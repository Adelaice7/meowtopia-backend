package com.rmeunier.catworld.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class ShopProduct {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "shop_product_id", updatable = false, nullable = false)
    private UUID shopProductId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    private ShopCategory category;

    @Column(name = "stock")
    private int stock;

    public ShopProduct(String name, double price, String description,
                       String image, ShopCategory category, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.category = category;
        this.stock = stock;
    }
}
