package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import com.example.speciality.Speciality;
import com.example.speciality.SpecialityService;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PokemonService {
  PokemonRepositary pokemonRepositary;
  SpecialityService specialityService;

  public PokemonService(PokemonRepositary pokemonRepositary, SpecialityService specialityService) {
    this.pokemonRepositary = pokemonRepositary;
    this.specialityService = specialityService;
  }

  public List<Pokemon> get() {
    return pokemonRepositary.findAll();
  }

  public Pokemon getById(Long id) {

    return pokemonRepositary
        .findById(id)
        .orElseThrow(() -> new PokemonValidationException("No such pokemon"));
  }

  public Pokemon create(PokemonCreateForm pokemonCreate) {
    Speciality speciality = specialityService.get(pokemonCreate.getSpecialityId());
    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonCreate.getName());
    pokemon.setImageUrl(pokemonCreate.getImageUrl());
    pokemon.setSpeciality(speciality);


    if (pokemon.getName() == null) {
      throw new PokemonValidationException("null value of Name is not allowed");
    } else if (existByName(pokemon)) {
      throw new PokemonValidationException("Pokemon with same name already exists");
    } else if (pokemon.getImageUrl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon.getSpeciality() == null) {
      throw new PokemonValidationException("null value of Speciality is not allowed");
    } else {

      return pokemonRepositary.save(pokemon);
    }
  }

  public Pokemon updatePokemon(Pokemon pokemon) {

    if (pokemon.getId() == null) {
      throw new PokemonValidationException("null value of Id is not allowed");
    } else if (pokemon.getName() == null) {
      throw new PokemonValidationException("null value of Name is not allowed");
    } else if (pokemon.getImageUrl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon.getSpeciality() == null) {
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
