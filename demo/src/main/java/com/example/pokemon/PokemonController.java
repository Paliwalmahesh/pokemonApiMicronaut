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

  @Get(value = "/{id}")
  public Pokemon getById(@PathVariable Long id) {
    return pokemonService.getById(id);
  }

  @Put
  public Pokemon update(@Body Pokemon pokemon) {
    return pokemonService.updatePokemon(pokemon);
  }

  @Post
  public Pokemon createPokemon(@Body Pokemon pokemon) {

    return pokemonService.create(pokemon);
  }

  @Delete(value = "/{id}")
  public void deletePokemon(@PathVariable Long id) {
    pokemonService.deletePokemon(id);
  }
}
