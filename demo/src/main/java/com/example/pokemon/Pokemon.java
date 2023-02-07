package com.example.pokemon;

import com.example.speciallity.Speciallity;

import javax.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  public Pokemon() {}

  private String name;
  @ManyToOne
  @JoinColumn(referencedColumnName = "id", name = "speciallity")
  private Speciallity speciallity;
  private String imageurl;

  public Pokemon(Long id, String name, Speciallity speciallity, String imageurl) {
    this.id = id;
    this.name = name;
    this.speciallity = speciallity;
    this.imageurl = imageurl;
  }

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

  public Speciallity getSpeciallity() {
    return speciallity;
  }

  public void setSpeciallity(Speciallity speciallity) {
    this.speciallity = speciallity;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }
}
