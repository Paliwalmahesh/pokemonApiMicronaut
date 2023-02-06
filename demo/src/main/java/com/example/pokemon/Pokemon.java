package com.example.pokemon;

public class Pokemon {
  private Long id;

  public Pokemon() {}

  public Pokemon(Long id, String name, String speciallity, String imageurl) {
    this.id = id;
    this.name = name;
    this.speciallity = speciallity;
    this.imageurl = imageurl;
  }

  private String name;
  private String speciallity;
  private String imageurl;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSpeciallity() {
    return speciallity;
  }

  public void setSpeciallity(String speciallity) {
    this.speciallity = speciallity;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }
}
