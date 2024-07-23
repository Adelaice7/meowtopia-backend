package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/foods")
public class FoodShopApi extends BaseShopApi<Food> {
    public FoodShopApi(IShopItemService<Food> shopItemService) {
        super(shopItemService);
    }
}

