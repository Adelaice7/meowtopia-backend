package com.rmeunier.catworld.shop.repo;

import com.rmeunier.catworld.shop.model.PetToy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetToyRepository extends JpaRepository<PetToy, UUID> {
}
