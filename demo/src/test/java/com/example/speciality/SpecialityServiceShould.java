package com.example.speciality;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
class SpecialityServiceShould {
    @Test
    void testGetSpeciality(){
        SpecialityRepositary specialityRepositary =Mockito.mock(SpecialityRepositary.class);
        Speciality speciality = new Speciality(1, "Grass");
        Mockito.when(specialityRepositary.findById(anyInt())).thenReturn(Optional.of(speciality));
        SpecialityService specialityService = new SpecialityService(specialityRepositary);
        Assertions.assertThat(specialityService.get(anyInt())).isEqualTo(speciality);

    }

}