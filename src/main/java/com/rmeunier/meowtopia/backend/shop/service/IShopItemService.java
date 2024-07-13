package com.rmeunier.meowtopia.backend.shop.service;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IShopItemService {
    ShopItem getShopItemById(UUID itemId);

    Page<ShopItem> getAllShopItems(Integer size, Integer page, String sort, String direction);

    ShopItem createShopItem(ShopItem shopItem);

    ShopItem updateShopItem(UUID shopItemId, ShopItem shopItem);

    boolean deleteShopItem(UUID itemId);

}
