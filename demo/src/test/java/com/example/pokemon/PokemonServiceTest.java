package com.example.pokemon;

import com.example.speciality.Speciality;
import com.example.speciality.SpecialityRepositary;
import com.example.speciality.SpecialityService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@MicronautTest
class PokemonServiceTest {
  PokemonService pokemonService;
  SpecialityService specialityService;
  SpecialityRepositary specialityRepositary;
  PokemonRepositary pokemonRepositary;
  PokemonCreateForm pokemonCreateForm;
  Pokemon pokemon_example1,pokemon_example2,pokemon_example3;

  List<Pokemon> pokemon_list=new ArrayList<>();

  Speciality speciality_example_1,speciality_example_2;

  @BeforeEach
  void setUp() {

    speciality_example_1 = new Speciality(1, "Grass");
    speciality_example_2 = new Speciality(2, "electric");
    pokemon_example1 = new Pokemon(1L, "Pikachu", speciality_example_1, "123");
    pokemon_example2 = new Pokemon(2L, "Paras", speciality_example_2, "1234");
    pokemon_example3 = new Pokemon(3L, "Bulbasaur", speciality_example_1, "12345");
    this.pokemon_list = List.of(pokemon_example1, pokemon_example2, pokemon_example3);
    pokemonRepositary = Mockito.mock(PokemonRepositary.class);
    specialityRepositary = Mockito.mock(SpecialityRepositary.class);
    specialityService = new SpecialityService(specialityRepositary);
    pokemonService=new PokemonService(pokemonRepositary,specialityService);
    pokemonCreateForm = new PokemonCreateForm("Bulbasaur",1,"12345");

  }

  @Test
  void getPokemons() {
    Mockito.when(pokemonRepositary.findAll()).thenReturn(pokemon_list);
    Assertions.assertThat(pokemon_list).isEqualTo(pokemonService.get());
  }

  @Test
  void testGetPokemonById() {
    Mockito.when(pokemonRepositary.findById(anyLong())).thenReturn(Optional.ofNullable(pokemon_example1));
    Assertions.assertThat(pokemon_example1).isEqualTo(pokemonService.getById(anyLong()));
  }

  @Test
  void testExistsByName(){
    Mockito.when(pokemonRepositary.findByName("Paras")).thenReturn(Optional.ofNullable(pokemon_example2));
    Assertions.assertThat(true).isEqualTo(pokemonService.existByName(pokemon_example2));
  }
  @Test
  void testCreate(){
    Mockito.when(pokemonRepositary.save(any(Pokemon.class))).thenReturn(pokemon_example3);
    Mockito.when(specialityRepositary.findById(1)).thenReturn(Optional.ofNullable(speciality_example_1));
    Assertions.assertThat(pokemonService.create(pokemonCreateForm)).isEqualTo(pokemon_example3);
  }
}

