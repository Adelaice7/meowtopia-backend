package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CatService {
    Cat findById(UUID id);

    List<Cat> getAllCats();

    Page<Cat> getAllCatsPaged(Pageable pageable);

    Cat createCat(UUID breedId, Cat cat);

    Cat modifyCat(UUID id, Cat updatedCat);

    void deleteCat(UUID id);

    List<Cat> findByBreedId(UUID breedId);

    List<Cat> findByBreedName(String breedName);

    void updateAllCatAges();
}
