package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class BaseShopApi<T extends ShopItem> {

    protected IShopItemService<T> shopItemService;

    @Autowired
    public BaseShopApi(IShopItemService<T> shopItemService) {
        this.shopItemService = shopItemService;
    }

    @GetMapping
    public ResponseEntity<List<T>> getAllItems() {
        return ResponseEntity.ok(shopItemService.getAllShopItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getItemById(@PathVariable UUID id) {
        return ResponseEntity.ok(shopItemService.getShopItemById(id));
    }

    @PostMapping
    public ResponseEntity<T> addItem(@RequestBody T item) {
        return ResponseEntity.ok(shopItemService.createShopItem(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> updateItem(@PathVariable UUID id, @RequestBody T item) {
        return ResponseEntity.ok(shopItemService.updateShopItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID id) {
        shopItemService.deleteShopItem(id);
        return ResponseEntity.noContent().build();
    }
}
