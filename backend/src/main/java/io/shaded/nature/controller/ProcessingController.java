package io.shaded.nature.controller;

import io.shaded.nature.model.QueueResponse;
import io.shaded.nature.service.ImageService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processing")
public final class ProcessingController {

  @Autowired
  private ImageService service;

  @GetMapping
  public List<QueueResponse> getQueue() {
    return this.service
      .all().entrySet().stream()
      .filter(uuidImageEntry -> uuidImageEntry.getValue().getAnimal() == null)
      .map(uuidImageEntry -> new QueueResponse(uuidImageEntry.getKey(),
        uuidImageEntry.getValue().getBase64()))
      .collect(Collectors.toList());
  }

}
