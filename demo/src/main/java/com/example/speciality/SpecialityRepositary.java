package com.example.speciality;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface SpecialityRepositary extends CrudRepository<Speciality,Integer> {


}
