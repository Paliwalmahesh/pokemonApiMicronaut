package com.example.pokemon;

import com.example.speciality.Speciality;

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
  @JoinColumn(referencedColumnName = "id", name = "speciality")
  private Speciality speciality;
  private String imageUrl;

  public Pokemon(Long id, String name, Speciality speciality, String imageUrl) {
    this.id = id;
    this.name = name;
    this.speciality = speciality;
    this.imageUrl = imageUrl;
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

  public Speciality getSpeciality() {
    return speciality;
  }

  public void setSpeciality(Speciality speciality) {
    this.speciality = speciality;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
