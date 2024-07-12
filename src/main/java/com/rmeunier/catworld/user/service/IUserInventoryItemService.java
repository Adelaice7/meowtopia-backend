package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserInventoryItem;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUserInventoryItemService {
    Page<UserInventoryItem> getInventoryItemsByUser(UUID userAccountId, Integer size, Integer page, String orderBy, String direction);

    UserInventoryItem getInventoryItemById(UUID inventoryItemId);

    UserInventoryItem saveInventoryItem(UserInventoryItem inventoryItem);

    boolean deleteInventoryItem(UUID inventoryItemId);
}
