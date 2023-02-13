package com.example.speciality;

import com.example.exception.PokemonValidationException;
import jakarta.inject.Singleton;

@Singleton
public class SpecialityService {
  private SpecialityRepositary specialityRepositary;

  public SpecialityService(SpecialityRepositary specialityRepositary) {
    this.specialityRepositary = specialityRepositary;
  }

  public Speciality get(Integer id) {
    return specialityRepositary
        .findById(id)
        .orElseThrow(
            () -> new PokemonValidationException("No Speciality Available for this " + id));
  }
}
