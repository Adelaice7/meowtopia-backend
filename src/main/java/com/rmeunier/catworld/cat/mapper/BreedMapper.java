package com.rmeunier.catworld.cat.mapper;

import com.rmeunier.catworld.cat.model.Breed;
import com.rmeunier.catworld.cat.model.dto.BreedDto;

public class BreedMapper {

    public static BreedDto mapToDto(Breed breed) {
        return BreedDto.builder()
                .name(breed.getName())
                .description(breed.getDescription())
                .lifeSpan(breed.getLifeSpan())
                .furType(breed.getFurType())
                .build();
    }

    public static Breed mapToEntity(BreedDto breedDto) {
        return Breed.builder()
                .name(breedDto.getName())
                .description(breedDto.getDescription())
                .lifeSpan(breedDto.getLifeSpan())
                .furType(breedDto.getFurType())
                .build();
    }
}
