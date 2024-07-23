package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.Drink;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/drinks")
public class DrinkShopApi extends BaseShopApi<Drink>{
    public DrinkShopApi(IShopItemService<Drink> shopItemService) {
        super(shopItemService);
    }
}
