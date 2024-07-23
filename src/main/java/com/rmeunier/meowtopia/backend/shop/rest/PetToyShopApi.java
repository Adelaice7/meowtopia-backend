package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/pettoys")
public class PetToyShopApi extends BaseShopApi<PetToy> {
    public PetToyShopApi(IShopItemService<PetToy> shopItemService) {
        super(shopItemService);
    }
}
