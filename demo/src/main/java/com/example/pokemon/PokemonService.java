package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import com.example.speciallity.Speciallity;
import com.example.speciallity.SpeciallityRepositary;
import com.example.speciallity.SpeciallityService;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PokemonService {
  PokemonRepositary pokemonRepositary;
  SpeciallityService speciallityService;

  public PokemonService(PokemonRepositary pokemonRepositary, SpeciallityService speciallityService) {
    this.pokemonRepositary = pokemonRepositary;
    this.speciallityService = speciallityService;
  }

  public List<Pokemon> get() {
    return pokemonRepositary.findAll();
  }

  public Pokemon getById(Long id) {

    return pokemonRepositary
        .findById(id)
        .orElseThrow(() -> new PokemonValidationException("No such pokemon"));
  }

  public Pokemon create(PokemonCreateForm pokemon) {
    Speciallity speciallity= speciallityService.get(pokemon.getSpeciallityId());
    Pokemon pokemon1 = new Pokemon();
    pokemon1.setName(pokemon.getName());
    pokemon1.setImageurl(pokemon.getImageUrl());
    pokemon1.setSpeciallity(speciallity);
    if (pokemon1.getName() == null) {
      throw new PokemonValidationException("null value of Name is not allowed");
    } else if (existByName(pokemon1)) {
      throw new PokemonValidationException("Pokemon with same name already exists");
    } else if (pokemon1.getImageurl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon1.getSpeciallity() == null) {
      throw new PokemonValidationException("null value of Speciality is not allowed");
    } else {

      return pokemonRepositary.save(pokemon1);
    }
  }

  public Pokemon updatePokemon(Pokemon pokemon) {

    if (pokemon.getId() == null) {
      throw new PokemonValidationException("null value of Id is not allowed");
    } else if (pokemon.getName() == null) {
      throw new PokemonValidationException("null value of Name is not allowed");
    } else if (pokemon.getImageurl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon.getSpeciallity() == null) {
      throw new PokemonValidationException("null value of Speciality is not allowed");
    } else if (existByName(pokemon)) {

      return pokemonRepositary.update(pokemon);
    } else {
      throw new PokemonValidationException("Pokemon Does not exist");
    }
  }

  public void deletePokemon(Long id) {
    Pokemon pokemon =
        pokemonRepositary
            .findById(id)
            .orElseThrow(() -> new PokemonValidationException("No such pokemon"));
    if (pokemon == null) {
      throw new PokemonValidationException("No pokemon found");
    } else {
      pokemonRepositary.delete(pokemon);
    }
  }

  public boolean existByName(Pokemon pokemon) {

    if (pokemonRepositary.findByName(pokemon.getName()).isPresent()) {
      return true;
    } else {
      return false;
    }
  }
}
