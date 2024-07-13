package com.rmeunier.meowtopia.backend.shop.service.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.repo.shopitems.IPetToyRepository;
import com.rmeunier.meowtopia.backend.shop.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
@Qualifier("petToyServiceImpl")
public class PetToyServiceImpl extends ProductServiceImpl<PetToy> {

    @Autowired
    public PetToyServiceImpl(IPetToyRepository petToyRepository) {
        super(petToyRepository);
    }
}