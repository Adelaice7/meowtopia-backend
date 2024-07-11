package com.rmeunier.catworld.cat.service;

import com.rmeunier.catworld.cat.model.dto.CatDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CatService {
    CatDto findById(UUID catId);

    List<CatDto> getAllCats();

    List<CatDto> getAllCatsByUserAccountId(UUID userAccountId);

    Page<CatDto> getAllCatsFiltered(Integer page, Integer size, String orderBy, String direction);

    Page<CatDto> getAllCatsByUserAccountIdFiltered(UUID userAccountId, Integer page, Integer size, String orderBy, String direction);

    CatDto createCat(UUID breedId, CatDto catDto);

    CatDto modifyCat(UUID catId, CatDto updatedCatDto);

    void deleteCat(UUID catId);

    List<CatDto> findByBreedId(UUID breedId);

    List<CatDto> findByBreedName(String breedName);

    void updateAllCatAges();
}
