package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.model.Breed;

import java.util.List;
import java.util.UUID;

public interface BreedService {
    List<Breed> getAllBreeds();

    Breed getBreedById(UUID breedId);

    Breed getBreedByName(String name);

    Breed createBreed(Breed breed);

    Breed updateBreed(UUID breedId, Breed breed);

    void deleteBreed(UUID breedId);
}
