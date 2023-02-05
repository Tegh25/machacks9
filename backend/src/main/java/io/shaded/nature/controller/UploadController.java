package io.shaded.nature.controller;

import io.shaded.nature.model.UploadRequest;
import io.shaded.nature.model.UploadResponse;
import io.shaded.nature.service.ImageService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uploads")
public final class UploadController {

  @Autowired
  private ImageService service;

  @PostMapping
  public ResponseEntity<UploadResponse> upload(
    @RequestBody UploadRequest body) {
    if (body.image() == null || body.image().isEmpty()) {
      // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/400
      return ResponseEntity.status(400).build();
    }

    final UUID uploadId = this.service.createImage(body.image());

    return ResponseEntity.ok(new UploadResponse(uploadId));
  }

}
