package com.example.pokemon;

import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class PokemonRepositary {
  private List<Pokemon> pokemonList;
  public static Long nextpokemonId;

  public PokemonRepositary() {
    pokemonList = new ArrayList<Pokemon>();
    nextpokemonId = 1L;
    this.addpokemon(new Pokemon(null, "bulbasaur", "grass", "imgurl"));
    this.addpokemon(new Pokemon(null, "pikachu", "grass", "imgurl"));
    this.addpokemon(new Pokemon(null, "pikachu2", "grass", "imgurl"));
  }

  public List<Pokemon> getPokemonList() {
    return Collections.unmodifiableList(pokemonList);
  }

  public Pokemon addpokemon(Pokemon pokemon) {
    pokemon.setId((nextpokemonId++));
    pokemonList.add(pokemon);
    return pokemon;
  }

  public Pokemon getpokemon(Long id) {
    return pokemonList.stream()
        .filter(pokemon -> pokemon.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public List<Pokemon> updatePokemonByID(Pokemon pokemon, Long id) {
    Pokemon updatedPokemon =
        pokemonList.stream()
            .filter(pokemonUpdate -> pokemonUpdate.getId().equals(id))
            .findFirst()
            .orElseThrow();
    updatedPokemon.setName(pokemon.getName());
    updatedPokemon.setImageurl(pokemon.getImageurl());
    updatedPokemon.setSpeciallity(pokemon.getSpeciallity());
    return Collections.unmodifiableList(pokemonList);
  }

  public List<Pokemon> deletePokemon(Long id) {
    Pokemon deletePokemon =
        pokemonList.stream().filter(pokemon -> pokemon.getId().equals(id)).findFirst().orElse(null);
    pokemonList.remove(deletePokemon);
    return Collections.unmodifiableList(pokemonList);
  }
}
