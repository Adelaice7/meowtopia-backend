package com.rmeunier.meowtopia.backend.shop.exception;

import com.rmeunier.meowtopia.backend.shop.model.Product;
import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {

        public ProductNotFoundException() {
            super("Could not find products");
        }

        public ProductNotFoundException(UUID productId) {
            super("Could not find product " + productId);
        }
}
