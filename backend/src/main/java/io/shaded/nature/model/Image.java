package io.shaded.nature.model;

public class Image {
  private String base64;
  private String animal;

  public Image(String base64, String animal) {
    this.base64 = base64;
    this.animal = animal;
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
}
