package com.example.pokemon;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface PokemonRepositary extends CrudRepository<Pokemon, Long> {

  //    public Pokemon create(Pokemon pokemon){
  //      save(pokemon);
  //    }
  //
  //    public List<Pokemon> getAll(){
  //      getAll();
  //    }

}
