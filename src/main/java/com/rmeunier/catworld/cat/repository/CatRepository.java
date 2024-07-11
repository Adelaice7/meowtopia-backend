package com.rmeunier.catworld.cat.repository;

import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findByBreedId(UUID breedId);
    List<Cat> findByBreedName(String breedName);
}
