package io.shaded.nature.service;

import io.shaded.nature.model.Image;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public final class ImageService {

  private final ConcurrentHashMap<UUID, Image> images =
    new ConcurrentHashMap<>();

  public UUID createImage(String base64) {
    final UUID id = UUID.randomUUID();
    final Image image = new Image(base64, null);

    this.images.put(id, image);

    return id;
  }

  public Image getImage(UUID id) {
    return this.images.get(id);
  }

  public Map<UUID, Image> all() {
    return Collections.unmodifiableMap(this.images);
  }

}
