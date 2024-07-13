package com.rmeunier.meowtopia.backend.shop.repo;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IShopItemRepository extends JpaRepository<ShopItem, UUID> {

}
