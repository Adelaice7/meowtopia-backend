package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CatService {
    Cat findById(UUID id);

    List<Cat> getAllCats();

    List<Cat> getAllCatsByUserAccountId(UUID userAccountId);

    Page<Cat> getAllCatsFiltered(Integer page, Integer size, String orderBy, String direction);

    Page<Cat> getAllCatsByUserAccountIdFiltered(UUID userAccountId, Integer page, Integer size, String orderBy, String direction);

    Cat createCat(UUID breedId, Cat cat);

    Cat modifyCat(UUID id, Cat updatedCat);

    void deleteCat(UUID id);

    List<Cat> findByBreedId(UUID breedId);

    List<Cat> findByBreedName(String breedName);

    void updateAllCatAges();
}
