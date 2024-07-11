package com.rmeunier.catworld.shop.service;

import com.rmeunier.catworld.shop.model.ShopProduct;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    // TODO: Implement shop service methods
    // Example: getAllProducts, getProductById, addProduct, updateProduct, deleteProduct
    List<ShopProduct> getAllProducts();

    ShopProduct getProductById(UUID shopProductId);

    ShopProduct addProduct(ShopProduct product);

    ShopProduct buyProduct(UUID shopProductId);

    ShopProduct updateProduct(UUID shopProductId, ShopProduct product);

    void deleteProduct(UUID shopProductId);
}
