package com.rmeunier.catworld.shop.repo;

import com.rmeunier.catworld.shop.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopItemRepository extends JpaRepository<ShopItem, UUID> {

}
