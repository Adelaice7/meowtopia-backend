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
        return shopRepository.findAll();
    }

    @Override
    public ShopProduct getProductById(UUID shopProductId) {
        return shopRepository.findById(shopProductId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public ShopProduct addProduct(ShopProduct product) {
        return shopRepository.save(product);
    }

    @Override
    public ShopProduct buyProduct(UUID shopProductId) {
        // TODO implement buyProduct
        // user inventory of items grows, shop stock of product shrinks
        // user currency lowers
        return null;
    }

    @Override
    public ShopProduct updateProduct(UUID shopProductId, ShopProduct product) {
        ShopProduct existingShopProduct = shopRepository.findById(shopProductId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingShopProduct.setName(product.getName());
        existingShopProduct.setPrice(product.getPrice());
        existingShopProduct.setStock(product.getStock());
        existingShopProduct.setDescription(product.getDescription());
        existingShopProduct.setImage(product.getImage());

        return shopRepository.save(existingShopProduct);
    }

    @Override
    public void deleteProduct(UUID shopProductId) {
        // TODO custom exception
        if (!shopRepository.existsById(shopProductId)) {
            throw new IllegalArgumentException("Product not found");
        } else {
            shopRepository.deleteById(shopProductId);
        }
    }
}
