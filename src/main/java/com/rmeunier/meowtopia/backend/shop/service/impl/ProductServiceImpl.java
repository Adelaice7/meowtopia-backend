package com.rmeunier.meowtopia.backend.shop.service.impl;

import com.rmeunier.meowtopia.backend.shop.exception.ProductNotFoundException;
import com.rmeunier.meowtopia.backend.shop.model.Product;
import com.rmeunier.meowtopia.backend.shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public abstract class ProductServiceImpl<T extends Product> implements IProductService<T> {

    private final JpaRepository<T, UUID> productRepository;

    @Autowired
    public ProductServiceImpl(JpaRepository<T, UUID> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<T> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public T getProductById(UUID productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public T createProduct(T product) {
        return productRepository.save(product);
    }

    @Override
    public T updateProduct(UUID productId, T product) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(productId);
        }

        return productRepository.save(product);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        if (!productRepository.existsById(productId)) {
            return false;
        }
        productRepository.deleteById(productId);
        return true;
    }
}