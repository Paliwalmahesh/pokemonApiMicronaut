package com.example.speciallity;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface SpeciallityRepositary extends CrudRepository<Speciallity,Integer> {


}
