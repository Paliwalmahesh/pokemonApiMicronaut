package com.example.pokemon;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PokemonRepositary extends CrudRepository<Pokemon, Long> {



}
