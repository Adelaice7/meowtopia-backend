package com.rmeunier.catworld.shop.service;

import com.rmeunier.catworld.shop.model.PetToy;
import com.rmeunier.catworld.shop.model.ShopItem;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IShopItemService {
    ShopItem getShopItemById(UUID itemId);

    Page<ShopItem> getAllShopItems(Integer size, Integer page, String sort, String direction);

    ShopItem createShopItem(ShopItem shopItem);

    boolean deleteShopItem(UUID itemId);

    // Specific methods for PetToy
    PetToy getPetToyById(UUID petToyId);

    PetToy createPetToy(PetToy petToy);

    boolean deletePetToy(UUID petToyId);

}
