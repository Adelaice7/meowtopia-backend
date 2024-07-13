package com.rmeunier.meowtopia.backend.shop.repo;

import com.rmeunier.meowtopia.backend.shop.exception.ShopItemNotFoundException;
import com.rmeunier.meowtopia.backend.shop.model.shopitems.PetToy;
import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.shop.repo.shopitems.PetToyRepository;
import com.rmeunier.meowtopia.backend.shop.service.IShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShopItemServiceImpl implements IShopItemService {

    private final ShopItemRepository shopItemRepository;

    private final PetToyRepository petToyRepository;

    @Autowired
    public ShopItemServiceImpl(ShopItemRepository shopItemRepository, PetToyRepository petToyRepository) {
        this.shopItemRepository = shopItemRepository;
        this.petToyRepository = petToyRepository;
    }

    @Override
    public ShopItem getShopItemById(UUID itemId) {
        return shopItemRepository.findById(itemId)
                .orElseThrow(() -> new ShopItemNotFoundException(itemId));
    }

    public Page<ShopItem> getAllShopItems(Integer size, Integer page, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return shopItemRepository.findAll(pageRequest);
    }

    @Override
    public ShopItem createShopItem(ShopItem shopItem) {
        return shopItemRepository.save(shopItem);
    }

    @Override
    public boolean deleteShopItem(UUID itemId) {
        if (shopItemRepository.existsById(itemId)) {
            shopItemRepository.deleteById(itemId);
            return true;
        }
        return false;
    }

    @Override
    public PetToy getPetToyById(UUID petToyId) {
        return petToyRepository.findById(petToyId)
                .orElseThrow(() -> new ShopItemNotFoundException(petToyId));
    }

    @Override
    public PetToy createPetToy(PetToy petToy) {
        return petToyRepository.save(petToy);
    }

    @Override
    public boolean deletePetToy(UUID petToyId) {
        if (petToyRepository.existsById(petToyId)) {
            petToyRepository.deleteById(petToyId);
            return true;
        }
        return false;
    }
}
