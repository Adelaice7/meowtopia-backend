package com.rmeunier.meowtopia.backend.user.model;

import com.rmeunier.meowtopia.backend.shop.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "user_inventory_items")
public class UserInventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_inventory_item_id")
    private UUID userInventoryItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", nullable = false)
    private UserAccount userAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "quantity")
    private int quantity;
}

