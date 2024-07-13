package com.rmeunier.meowtopia.backend.shop.service.shopitems;

import com.rmeunier.meowtopia.backend.shop.exception.ProductNotFoundException;
import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;
import com.rmeunier.meowtopia.backend.shop.repo.shopitems.IFoodRepository;
import com.rmeunier.meowtopia.backend.shop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Qualifier("foodServiceImpl")
public class FoodServiceImpl implements IProductService<Food> {
    private final IFoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(IFoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllProducts() {
        return foodRepository.findAll();
    }

    @Override
    public Food getProductById(UUID productId) {
        return foodRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
    }

    @Override
    public Food createProduct(Food product) {
        return foodRepository.save(product);
    }

    @Override
    public Food updateProduct(UUID productId, Food product) {
        return foodRepository.save(product);
    }

    @Override
    public boolean deleteProduct(UUID productId) {
        if (foodRepository.existsById(productId)) {
            foodRepository.deleteById(productId);
            return true;
        }
        return false;
    }
}
