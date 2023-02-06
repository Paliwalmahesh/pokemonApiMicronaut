package com.example.pokemon;

import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class PokemonService {
  PokemonRepositary pokemonRepositary;

  public PokemonService(PokemonRepositary pokemonRepositary) {
    this.pokemonRepositary = pokemonRepositary;
  }

  public List<Pokemon> get() {
    return pokemonRepositary.getPokemonList();
  }

  public Pokemon create(Pokemon pokemon) {

    return pokemonRepositary.addpokemon(pokemon);
  }

  public Pokemon getPokemon(Long id) {
    return pokemonRepositary.getpokemon(id);
  }

  public List<Pokemon> updatePokemon(Pokemon pokemon, Long id) {
    return pokemonRepositary.updatePokemonByID(pokemon, id);
  }

  public List<Pokemon> deletePokemon(Long id) {
    return pokemonRepositary.deletePokemon(id);
  }
}
