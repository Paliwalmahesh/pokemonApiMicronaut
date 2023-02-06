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

  public Pokemon create(Pokemon pokemon) {

    return pokemonRepositary.save(pokemon);
  }



//
//  public List<Pokemon> updatePokemon(Pokemon pokemon, Long id) {
//    return pokemonRepositary.updatePokemonByID(pokemon, id);
//  }
//
//  public List<Pokemon> deletePokemon(Long id) {
//    return pokemonRepositary.deletePokemon(id);
//  }
}
