package com.rmeunier.meowtopia.backend.shop.repo.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.repo.IProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetToyRepository extends IProductRepository<PetToy> {
}
