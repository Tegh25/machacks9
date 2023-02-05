package io.shaded.nature.controller;

import io.shaded.nature.model.Image;
import io.shaded.nature.service.ImageService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class ImageController {

  @Autowired
  private ImageService service;

  @GetMapping(value = "/images/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Image getImage(@PathVariable UUID id) {
    System.out.println(id);
    return this.service.getImage(id);
  }
}
