package com.rmeunier.meowtopia.backend.shop.model.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PetToy extends ShopItem {
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public PetToy(String name, Double price, int stock, String description) {
        super(name, price, stock);
        this.description = description;
    }
}