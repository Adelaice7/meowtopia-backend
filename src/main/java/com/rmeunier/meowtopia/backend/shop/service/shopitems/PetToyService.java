package com.rmeunier.meowtopia.backend.shop.service.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.repo.shopitems.PetToyRepository;
import com.rmeunier.meowtopia.backend.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PetToyService extends ProductService<PetToy> {

    @Autowired
    public PetToyService(PetToyRepository petToyRepository) {
        super(petToyRepository);
    }
}