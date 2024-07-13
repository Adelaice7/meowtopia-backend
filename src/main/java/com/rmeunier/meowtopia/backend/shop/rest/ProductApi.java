package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.shopitems.Food;
import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.service.shopitems.FoodServiceImpl;
import com.rmeunier.meowtopia.backend.shop.service.shopitems.PetToyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductApi {

    private final FoodServiceImpl foodService;
    private final PetToyServiceImpl petToyService;

    @Autowired
    public ProductApi(FoodServiceImpl foodService, PetToyServiceImpl petToyService) {
        this.foodService = foodService;
        this.petToyService = petToyService;
    }

    @GetMapping("/foods")
    public ResponseEntity<List<Food>> getAllFoods() {
        return ResponseEntity.ok(foodService.getAllProducts());
    }

    @GetMapping("/pet-toys")
    public ResponseEntity<List<PetToy>> getAllPetToys() {
        return ResponseEntity.ok(petToyService.getAllProducts());
    }

    @GetMapping("/foods/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") UUID productId) {
        return ResponseEntity.ok(foodService.getProductById(productId));
    }

    @GetMapping("/pet-toys/{id}")
    public ResponseEntity<PetToy> getPetToyById(@PathVariable("id") UUID productId) {
        return ResponseEntity.ok(petToyService.getProductById(productId));
    }

    @PostMapping("/foods")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodService.createProduct(food));
    }

    @PostMapping("/pet-toys")
    public ResponseEntity<PetToy> createPetToy(@RequestBody PetToy petToy) {
        return ResponseEntity.ok(petToyService.createProduct(petToy));
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable("id") UUID productId, @RequestBody Food food) {
        return ResponseEntity.ok(foodService.updateProduct(productId, food));
    }

    @PutMapping("/pet-toys/{id}")
    public ResponseEntity<PetToy> updatePetToy(@PathVariable("id") UUID productId, @RequestBody PetToy petToy) {
        return ResponseEntity.ok(petToyService.updateProduct(productId, petToy));
    }

    @DeleteMapping("/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable("id") UUID productId) {
        if (foodService.deleteProduct(productId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/pet-toys/{id}")
    public ResponseEntity<Void> deletePetToy(@PathVariable("id") UUID productId) {
        if (petToyService.deleteProduct(productId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
