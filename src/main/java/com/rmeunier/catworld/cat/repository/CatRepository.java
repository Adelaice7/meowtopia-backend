package com.rmeunier.catworld.cat.repository;

import com.rmeunier.catworld.cat.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatRepository extends JpaRepository<Cat, UUID> {
    List<Cat> findByBreedBreedId(UUID breedId);
    Page<Cat> findByBreedBreedId(UUID breedId, Pageable pageable);
    List<Cat> findByUserAccountUserAccountId(UUID userAccountId);
    Page<Cat> findByUserAccountUserAccountId(UUID userAccountId, Pageable pageable);
}
