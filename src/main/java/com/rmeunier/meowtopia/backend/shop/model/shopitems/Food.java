package com.rmeunier.meowtopia.backend.shop.model.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food extends Product {
    public Food(String name, Double price) {
        super(name, price);
    }
}