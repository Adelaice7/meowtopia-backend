package com.rmeunier.catworld.shop.model;

import com.rmeunier.catworld.user.model.UserAccount;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@Entity
@Table(name = "pet_toys")
public class PetToy extends ShopProduct {
    @Column(name = "quantity")
    private int quantity;

    @ManyToMany(mappedBy = "petToys", fetch = FetchType.LAZY)
    private Set<UserAccount> users;

    public PetToy(String name, double price, int quantity,
                  String image, String description, int stock) {
        super(name, price, description, image, ShopCategory.TOYS, stock);
        this.quantity = quantity;
    }
}
