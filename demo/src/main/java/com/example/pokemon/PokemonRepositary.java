package com.example.pokemon;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PokemonRepositary extends CrudRepository<Pokemon, Long> {
  Optional<Pokemon> findByName(String Name);

  List<Pokemon> findAll();
}
