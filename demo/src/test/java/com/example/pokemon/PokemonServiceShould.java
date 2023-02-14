package com.example.pokemon;

import com.example.exception.PokemonValidationException;
import com.example.speciality.Speciality;
import com.example.speciality.SpecialityRepositary;
import com.example.speciality.SpecialityService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;


class PokemonServiceShould {
  PokemonService pokemonService;
  SpecialityService specialityService;
  SpecialityRepositary specialityRepositary;
  PokemonRepositary pokemonRepositary;
  PokemonCreateForm pokemonCreateForm;
  Pokemon pokemon_example1, pokemon_example2, pokemon_example3;

  List<Pokemon> pokemon_list = new ArrayList<>();

  Speciality speciality_example_1, speciality_example_2;

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
    pokemonService = new PokemonService(pokemonRepositary, specialityService);
    pokemonCreateForm = new PokemonCreateForm("Bulbasaur", 1, "546");
  }

  @Test
  void getPokemons() {
    Mockito.when(pokemonRepositary.findAll()).thenReturn(pokemon_list);
    Assertions.assertThat(pokemon_list).isEqualTo(pokemonService.get());
  }

  @Test
  void testGetPokemonById() {
    Mockito.when(pokemonRepositary.findById(anyLong()))
        .thenReturn(Optional.ofNullable(pokemon_example1));
    Assertions.assertThat(pokemon_example1).isEqualTo(pokemonService.getById(anyLong()));
  }

  @Test
  void testExistsByName() {
    Mockito.when(pokemonRepositary.findByName("Paras"))
        .thenReturn(Optional.ofNullable(pokemon_example2));
    Assertions.assertThat(true).isEqualTo(pokemonService.existByName(pokemon_example2));
  }

  @Test
  void testCreate() {
    Mockito.when(pokemonRepositary.save(any(Pokemon.class))).thenReturn(pokemon_example3);
    Mockito.when(specialityRepositary.findById(1))
        .thenReturn(Optional.ofNullable(speciality_example_1));
    Assertions.assertThat(pokemonService.create(pokemonCreateForm)).isEqualTo(pokemon_example3);
  }

  @Test
  void testUpdate() {
    Mockito.when(pokemonRepositary.update(any(Pokemon.class))).thenReturn(pokemon_example1);
    Mockito.when(pokemonRepositary.findByName(anyString()))
        .thenReturn(Optional.ofNullable(pokemon_example1));
    Assertions.assertThat(pokemonService.updatePokemon(pokemon_example1))
        .isEqualTo(pokemon_example1);
  }

  @Test
  void testDelete() {
    Mockito.when(pokemonRepositary.findById(anyLong()))
        .thenReturn(Optional.ofNullable(pokemon_example1));
    pokemonService.deletePokemon(anyLong());
    Mockito.verify(pokemonRepositary).findById(anyLong());
  }

  @Test
  void shouldThrowExceptionIfPokemonAlreadyExistOnCreate() {
    Mockito.when(pokemonRepositary.findByName(anyString()))
        .thenReturn(Optional.ofNullable(pokemon_example1));
    Mockito.when(specialityRepositary.findById(1))
        .thenReturn(Optional.ofNullable(speciality_example_1));
    pokemonService.existByName(pokemon_example2);
    Assertions.assertThatThrownBy(() -> pokemonService.create(pokemonCreateForm))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("Pokemon already exist");
  }

  @Test
  void shouldThrowExceptionOfNullInputName() {
    PokemonCreateForm pokemonCreateForm1 = new PokemonCreateForm(null, 1, "gfdg");
    Mockito.when(specialityRepositary.findById(1))
        .thenReturn(Optional.ofNullable(speciality_example_1));
    Assertions.assertThatThrownBy(() -> pokemonService.create(pokemonCreateForm1))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("null value of Name is not allowed");
  }

  @Test
  void shouldThrowExceptionOfNullInputImageUrl() {
    pokemonCreateForm = new PokemonCreateForm("name", 1, null);
    Mockito.when(specialityRepositary.findById(1))
        .thenReturn(Optional.ofNullable(speciality_example_1));
    Assertions.assertThatThrownBy(() -> pokemonService.create(pokemonCreateForm))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("null value of ImageUrl is not allowed");
  }

  @Test
  void shouldThrowExceptionOfNullInputSpeciality() {
    pokemonCreateForm = new PokemonCreateForm("hgj", 1, "gfdg");
    Mockito.when(specialityRepositary.findById(1)).thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.create(pokemonCreateForm))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("No Speciality Available for this 1");
  }

  @Test
  void shouldThrowExceptionOfInputPokemonNotFound() {

    Mockito.when(pokemonRepositary.update(any(Pokemon.class))).thenReturn(pokemon_example1);
    Mockito.when(pokemonRepositary.findByName(anyString())).thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.updatePokemon(pokemon_example1))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("Pokemon Does not exist");
  }
  @Test
  void shouldThrowExceptionOfInputPokemonNull() {
    pokemon_example1 =new Pokemon(1L,"Pikachu",null,"132");
    Mockito.when(pokemonRepositary.update(any(Pokemon.class))).thenReturn(pokemon_example1);
    Mockito.when(pokemonRepositary.findByName(anyString())).thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.updatePokemon(pokemon_example1))
            .isInstanceOf((PokemonValidationException.class))
            .hasMessage("null value of Speciality is not allowed");
  }
  @Test
  void shouldThrowExceptionOfInputPokemonIdNotExist() {
    Mockito.when(pokemonRepositary.findById(anyLong()))
            .thenReturn(Optional.ofNullable(null));
    Assertions.assertThatThrownBy(() -> pokemonService.deletePokemon(anyLong()))
        .isInstanceOf((PokemonValidationException.class))
        .hasMessage("No such pokemon");
  }
}
