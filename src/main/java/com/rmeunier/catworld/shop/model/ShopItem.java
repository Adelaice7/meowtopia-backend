package com.rmeunier.catworld.shop.model;

import com.rmeunier.catworld.user.model.Product;
import com.rmeunier.catworld.user.model.UserInventoryItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@ToString
public class ShopItem extends Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "shop_product_id", updatable = false, nullable = false)
    private UUID shopProductId;
}
