package com.example.pokemon;

import io.micronaut.http.annotation.*;

import java.util.List;

@Controller("/pokemon")
public class PokemonController {
  private final PokemonService pokemonService;

  public PokemonController(PokemonService pokemonService) {
    this.pokemonService = pokemonService;
  }

  @Get
  public List<Pokemon> getPokemons() {

    return pokemonService.get();
  }
  ;

//  @Get(value = "/{id}")
//  public Pokemon getById(@PathVariable Long id) {
//    return pokemonService.getPokemon(id);
//  }

//  @Put(value = "/{id}")
//  public List<Pokemon> updateById(@Body Pokemon pokemon, @PathVariable Long id) {
//    return pokemonService.updatePokemon(pokemon, id);
//  }

  @Post
  public Pokemon createPokemon(@Body Pokemon pokemon) {
    return pokemonService.create(pokemon);
  }

//  @Delete(value = "/{id}")
//  public List<Pokemon> deletePokemon(@PathVariable Long id) {
//    return pokemonService.deletePokemon(id);
//  }
}
