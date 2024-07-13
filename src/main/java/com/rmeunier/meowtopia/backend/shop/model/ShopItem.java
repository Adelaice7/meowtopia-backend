package com.rmeunier.meowtopia.backend.shop.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.BindParam;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "shop_items")
public class ShopItem extends Product {

    // Constructors
    public ShopItem(String name, Double price) {
        super(name, price);
    }
}
