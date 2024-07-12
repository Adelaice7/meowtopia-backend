package com.rmeunier.catworld.user.model;

import com.rmeunier.catworld.shop.model.ShopItem;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_inventory_items")
public class UserInventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long userInventoryItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount user_account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}

