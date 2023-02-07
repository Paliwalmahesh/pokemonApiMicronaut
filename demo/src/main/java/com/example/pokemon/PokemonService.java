package com.example.pokemon;

import jakarta.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PokemonService {
  PokemonRepositary pokemonRepositary;

  public PokemonService(PokemonRepositary pokemonRepositary) {
    this.pokemonRepositary = pokemonRepositary;
  }

  public List<Pokemon> get() {
    List<Pokemon> pokemons = new ArrayList<>();
    for (Pokemon pokemon : pokemonRepositary.findAll()) {
      pokemons.add(pokemon);
    }
    return pokemons;
  }

  public Pokemon getById(Long id) {
    Pokemon pokemon =
        pokemonRepositary
            .findById(id)
            .orElseThrow(() -> new PokemonValidationException("No such pokemon"));

    return pokemon;
  }

  public Pokemon create(Pokemon pokemon) {
    if (pokemon.getName() == null) {
      throw new PokemonValidationException("null value of Name is not allowed");
    } else if (pokemon.getImageurl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon.getSpeciallity() == null) {
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
    } else if (pokemon.getImageurl() == null) {
      throw new PokemonValidationException("null value of ImageUrl is not allowed");
    } else if (pokemon.getSpeciallity() == null) {
      throw new PokemonValidationException("null value of Speciality is not allowed");
    } else {

      return pokemonRepositary.update(pokemon);
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
}
