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
    List<Pokemon>pokemons=new ArrayList<>();
    for(Pokemon pokemon:pokemonRepositary.findAll()){
      pokemons.add(pokemon);
    }

    return pokemons;
  }
  public Pokemon getById(Long id) {

    return pokemonRepositary.findById(id).orElseThrow();
  }


  public Pokemon create(Pokemon pokemon) {

    return pokemonRepositary.save(pokemon);
  }




  public Pokemon updatePokemon(Pokemon pokemon) {
    return pokemonRepositary.update(pokemon);
  }

  public void deletePokemon(Long id) {
    Pokemon pokemon=pokemonRepositary.findById(id).orElseThrow();
    pokemonRepositary.delete(pokemon);
  }
}
