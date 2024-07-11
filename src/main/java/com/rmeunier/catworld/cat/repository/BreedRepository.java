package com.rmeunier.catworld.cat.repository;

import com.rmeunier.catworld.cat.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BreedRepository extends JpaRepository<Breed, UUID> {

    Optional<Breed> findBreedByName(String name);
}
