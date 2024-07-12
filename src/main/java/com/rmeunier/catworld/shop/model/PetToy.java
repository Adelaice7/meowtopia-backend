package com.rmeunier.catworld.shop.model;

import com.rmeunier.catworld.user.model.Product;
import com.rmeunier.catworld.user.model.UserAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "pet_toys")
public class PetToy extends Product {
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public PetToy(String name, Double price, String description) {
        super(name, price);
        this.description = description;
    }
}
