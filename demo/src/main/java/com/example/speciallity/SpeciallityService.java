package com.example.speciallity;

import com.example.exception.PokemonValidationException;
import jakarta.inject.Singleton;

@Singleton
public class SpeciallityService {
    private SpeciallityRepositary speciallityRepositary;

    public SpeciallityService(SpeciallityRepositary speciallityRepositary) {
        this.speciallityRepositary = speciallityRepositary;
    }
    public Speciallity get(Integer id){
       return speciallityRepositary.findById(id).orElseThrow(()-> new PokemonValidationException("No Speciality Available for this "+ id));
    }
}
