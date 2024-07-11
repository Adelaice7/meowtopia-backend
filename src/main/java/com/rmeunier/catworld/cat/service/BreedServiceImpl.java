package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.repository.BreedRepository;
import com.rmeunier.catworld.cat.exception.BreedNotFoundException;
import com.rmeunier.catworld.cat.model.Breed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BreedServiceImpl implements BreedService{
    private final BreedRepository breedRepository;

    @Autowired
    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    @Override
    public List<Breed> getAllBreeds() {
        List<Breed> allBreeds = breedRepository.findAll();
        if (allBreeds.isEmpty()) {
            throw new BreedNotFoundException();
        }
        return allBreeds;
    }

    @Override
    public Breed getBreedById(UUID breedId) {
        return breedRepository.findById(breedId)
                .orElseThrow(() -> new BreedNotFoundException(breedId));
    }

    @Override
    public Breed createBreed(Breed breed) {
        // TODO validation
        return breedRepository.save(breed);
    }

    @Override
    public Breed updateBreed(UUID breedId, Breed breed) {
        Breed existingBreed = breedRepository.findById(breedId)
                .orElseThrow(() -> new BreedNotFoundException(breedId));

        // TODO validation
        existingBreed.setName(breed.getName());
        existingBreed.setDescription(breed.getDescription());
        existingBreed.setLifeSpan(breed.getLifeSpan());
        existingBreed.setFurType(breed.getFurType());

        return breedRepository.save(existingBreed);
    }

    @Override
    public void deleteBreed(UUID breedId) {
        if (!breedRepository.existsById(breedId)) {
            throw new BreedNotFoundException(breedId);
        }
        breedRepository.deleteById(breedId);
    }
}
