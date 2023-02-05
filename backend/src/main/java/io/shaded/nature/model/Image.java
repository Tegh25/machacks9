package io.shaded.nature.model;

public class Image {
  private String base64;
  private String animal;
  private AnimalStatus status;

  public Image(String base64, String animal) {
    this.base64 = base64;
    this.animal = animal;
    this.status = AnimalStatus.UNKNOWN;
  }

  public String getBase64() {
    return base64;
  }

  public void setBase64(String base64) {
    this.base64 = base64;
  }

  public String getAnimal() {
    return animal;
  }

  public void setAnimal(String animal) {
    this.animal = animal;
  }

  public AnimalStatus getStatus() {
    return status;
  }

  public void setStatus(AnimalStatus status) {
    this.status = status;
  }

}
