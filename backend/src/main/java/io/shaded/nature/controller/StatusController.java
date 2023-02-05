package io.shaded.nature.controller;

import io.shaded.nature.model.AnimalStatus;
import io.shaded.nature.model.Image;
import io.shaded.nature.model.Status;
import io.shaded.nature.model.StatusGetResponse;
import io.shaded.nature.model.StatusUpdateRequest;
import io.shaded.nature.service.ImageService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Locale;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status/{id}")
public final class StatusController {

  private static final HttpClient httpClient = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(10))
    .build();


  @Autowired
  private ImageService service;

  @GetMapping
  public ResponseEntity<StatusGetResponse> getStatus(@PathVariable UUID id) {
    final Image image = service.getImage(id);

    if (image == null) {
      return ResponseEntity.notFound().build();
    }

    final Status status = image.getAnimal() == null ? Status.PROCESSING :
      Status.DONE;

    return ResponseEntity.ok(new StatusGetResponse(status));
  }

  @PostMapping
  public void updateStatus(@PathVariable UUID id,
    @RequestBody StatusUpdateRequest request)
    throws IOException, InterruptedException {

    final Image image = service.getImage(id);

    if (image == null) {
      return;
    }

    HttpRequest httpRequest = HttpRequest.newBuilder()
      .GET()
      .uri(URI.create(
        "https://en.wikipedia.org/api/rest_v1/page/segments/" + request.type()))
      .build();

    String body = httpClient.send(httpRequest,
      HttpResponse.BodyHandlers.ofString()).body();

    body = body.toLowerCase(Locale.ROOT);

    AnimalStatus status = AnimalStatus.UNKNOWN;

    if (body.contains("endangered")) {
      status = AnimalStatus.ENDANGERED;
    } else if (body.contains("extinct")) {
      status = AnimalStatus.EXTINCT;
    }

    image.setStatus(status);
    image.setAnimal(request.type());
  }

}
