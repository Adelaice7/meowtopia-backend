package com.rmeunier.meowtopia.backend.shop.model.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PetToy extends Product {
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public PetToy(String name, Double price, String description) {
        super(name, price);
        this.description = description;
    }
}