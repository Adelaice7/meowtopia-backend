package com.rmeunier.meowtopia.backend.shop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID shopItemId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock")
    private int stock;

    public ShopItem(String name, Double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
