package com.rmeunier.meowtopia.backend.shop.rest;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.shop.service.IProductService;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import com.rmeunier.meowtopia.backend.shop.service.shopitems.FoodServiceImpl;
import com.rmeunier.meowtopia.backend.shop.service.shopitems.PetToyServiceImpl;
import com.rmeunier.meowtopia.backend.user.service.IUserInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/shop")
public class ShopApi {

    private final IShopItemService shopItemService;

    private final IUserInventoryService userInventoryService;

    private final FoodServiceImpl foodService;

    private PetToyServiceImpl petToyService;

    @Autowired
    public ShopApi(IShopItemService shopItemService,
                   IUserInventoryService userInventoryService,
                   FoodServiceImpl foodService,
                   PetToyServiceImpl petToyService) {
        this.shopItemService = shopItemService;
        this.userInventoryService = userInventoryService;
        this.foodService = foodService;
        this.petToyService = petToyService;
    }

    // Add more endpoints for shopping, buying products, etc.
    // Example: get all products, get product by id, buy product, etc.

    @GetMapping("/products/{category}")
    public ResponseEntity<List<?>> getProductsByCategory(@PathVariable("category") String productCategory) {
        IProductService<?> service = getProductService(productCategory);
        if (service == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getAllProducts());
    }

    @PostMapping("/buy/{category}/{productId}")
    public String buyProduct(@PathVariable("category") String category,
                             @PathVariable("productId") UUID productId,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("userId") UUID userId) {

        ShopItem shopItem = shopItemService.getShopItemById(productId);

        if (shopItem.getStock() < quantity) {
            return "Insufficient stock";
        }

        // Update stock in the shop
        shopItem.setStock(shopItem.getStock() - quantity);
        shopItemService.updateShopItem(shopItem.getProductId(), shopItem);

        // Add to user's inventory
        userInventoryService.addProductToInventory(userId, shopItem, quantity);

        return "Product purchased successfully";

    }
    private IProductService<?> getProductService(String category) {
        return switch (category.toLowerCase()) {
            case "food" -> foodService;
            case "pet-toy" -> petToyService;
            default -> null;
        };
    }
}
