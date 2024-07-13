package com.rmeunier.meowtopia.backend.shop.repo.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.repo.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetToyRepository extends ProductRepository<PetToy> {
}
