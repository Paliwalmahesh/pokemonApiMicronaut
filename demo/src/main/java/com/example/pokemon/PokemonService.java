package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import com.example.speciality.Speciality;
import com.example.speciality.SpecialityService;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
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

  String pokemonValidate(Pokemon pokemon) {
    if (pokemon.getName() == null) {
      return "null value of Name is not allowed";
    } else if (pokemon.getImageUrl() == null) {
      return "null value of ImageUrl is not allowed";
    } else if (pokemon.getSpeciality() == null) {
      return "null value of Speciality is not allowed";
    }
    return null;
  }

  public Pokemon create(PokemonCreateForm pokemonCreate) {
    Speciality speciality = specialityService.get(pokemonCreate.getSpecialityId());
    Pokemon pokemon = new Pokemon();
    pokemon.setName(pokemonCreate.getName());
    pokemon.setImageUrl(pokemonCreate.getImageUrl());
    pokemon.setSpeciality(speciality);
    if (existByName(pokemon)) {
      throw new PokemonValidationException("Pokemon already exist");
    } else if (pokemonValidate(pokemon) != null) {
      throw new PokemonValidationException(pokemonValidate(pokemon));

    } else {

      return pokemonRepositary.save(pokemon);
    }
  }

  public Pokemon updatePokemon(Pokemon pokemon) {
    if (pokemonValidate(pokemon) != null) {
      throw new PokemonValidationException(pokemonValidate(pokemon));
    } else if (existByName(pokemon)) {
      return pokemonRepositary.update(pokemon);
    } else {
      throw new PokemonValidationException("Pokemon Does not exist");
    }
  }

  public void deletePokemon(Long id) {
    Pokemon pokemon = getById(id);

    pokemonRepositary.delete(pokemon);
  }

  public boolean existByName(Pokemon pokemon) {

    if (pokemonRepositary.findByName(pokemon.getName()).isPresent()) {
      return true;
    } else {
      return false;
    }
  }
}
