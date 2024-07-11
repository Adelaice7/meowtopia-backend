package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.model.Breed;
import com.rmeunier.catworld.cat.model.dto.BreedDto;

import java.util.List;
import java.util.UUID;

public interface BreedService {
    List<BreedDto> getAllBreeds();

    BreedDto getBreedById(UUID breedId);

    BreedDto searchBreedByName(String name);

    BreedDto createBreed(BreedDto breed);

    BreedDto updateBreed(UUID breedId, BreedDto breed);

    void deleteBreed(UUID breedId);
}
