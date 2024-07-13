package com.rmeunier.meowtopia.backend.shop.repo.shopitems;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;
import com.rmeunier.meowtopia.backend.shop.repo.IProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodRepository extends IProductRepository<Food> {
}
