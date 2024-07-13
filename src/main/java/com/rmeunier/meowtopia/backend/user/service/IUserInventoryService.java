package com.rmeunier.meowtopia.backend.user.service;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.user.model.UserInventoryItem;

import java.util.List;
import java.util.UUID;

public interface IUserInventoryService {

    UserInventoryItem addProductToInventory(UUID userAccountId, ShopItem product, Integer quantity);

    List<UserInventoryItem> getInventoryItemsByUser(UUID userAccountId);

    UserInventoryItem getInventoryItemById(UUID inventoryItemId);

    UserInventoryItem saveInventoryItem(UserInventoryItem inventoryItem);

    boolean deleteInventoryItem(UUID inventoryItemId);
}
