package com.rmeunier.meowtopia.backend.shop.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_items")
public class ShopItem extends Product {

    @Column(name = "stock")
    private int stock;

    // Constructors
    public ShopItem(String name, Double price, int stock) {
        super(name, price);
        this.stock = stock;
    }
}
