package com.rmeunier.meowtopia.backend.shop.service;

import com.rmeunier.meowtopia.backend.shop.model.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService<T extends Product> {
    List<T> getAllProducts();

    T getProductById(UUID productId);

    T createProduct(T product);

    T updateProduct(UUID productId, T product);

    boolean deleteProduct(UUID productId);
}
