package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/shop")
public class GeneralShopApi extends BaseShopApi<ShopItem> {
    public GeneralShopApi(IShopItemService<ShopItem> shopItemService) {
        super(shopItemService);
    }

    @GetMapping
    public ResponseEntity<List<ShopItem>> getAllItems() {
        return ResponseEntity.ok(shopItemService.getAllShopItems());
    }
}
