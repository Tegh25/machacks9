package io.shaded.nature.controller;

import io.shaded.nature.model.Image;
import io.shaded.nature.model.Status;
import io.shaded.nature.model.StatusGetResponse;
import io.shaded.nature.model.StatusUpdateRequest;
import io.shaded.nature.service.ImageService;
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
    @RequestBody StatusUpdateRequest request) {

    final Image image = service.getImage(id);

    if (image == null) {
      return;
    }

    image.setAnimal(request.type());
  }

}
