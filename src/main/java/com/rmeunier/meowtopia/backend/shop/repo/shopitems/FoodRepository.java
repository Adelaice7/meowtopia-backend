package com.rmeunier.meowtopia.backend.shop.repo.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;
import com.rmeunier.meowtopia.backend.shop.repo.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ProductRepository<Food> {
}
