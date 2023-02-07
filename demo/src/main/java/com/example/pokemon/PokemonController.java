package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
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

  @Get(value = "/{id}")
  public HttpResponse<Pokemon> getById(@PathVariable Long id) {
    return HttpResponse.ok(pokemonService.getById(id));
  }

  @Put
  public Pokemon update(@Body Pokemon pokemon) {
    return pokemonService.updatePokemon(pokemon);
  }

  @Post
  public HttpResponse<Pokemon> createPokemon(@Body PokemonCreateForm pokemon) {

    return HttpResponse.created(pokemonService.create(pokemon));
  }

  @Delete(value = "/{id}")
  public HttpResponse<String> deletePokemon(@PathVariable Long id) {
    try {
      pokemonService.deletePokemon(id);
      return HttpResponse.ok("Deleted");
    } catch (PokemonValidationException e) {
      return HttpResponse.badRequest(e.getMessage());
    }
  }
}
