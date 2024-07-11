package com.rmeunier.catworld.shop.service;

import com.rmeunier.catworld.shop.model.ShopProduct;
import com.rmeunier.catworld.shop.repo.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    public List<ShopProduct> getAllProducts() {
        return List.of();
    }

    @Override
    public ShopProduct getProductById(UUID shopProductId) {
        return null;
    }

    @Override
    public ShopProduct addProduct(ShopProduct product) {
        return null;
    }

    @Override
    public ShopProduct buyProduct(UUID shopProductId) {
        return null;
    }

    @Override
    public ShopProduct updateProduct(ShopProduct product) {
        return null;
    }

    @Override
    public void deleteProduct(UUID shopProductId) {
        // TODO implement deleteProduct
    }
}
