package com.rmeunier.catworld.shop.repo;

import com.rmeunier.catworld.shop.model.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShopRepository extends JpaRepository<ShopProduct, UUID> {

}
