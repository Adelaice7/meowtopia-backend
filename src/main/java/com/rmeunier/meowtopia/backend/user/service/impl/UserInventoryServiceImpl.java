package com.rmeunier.meowtopia.backend.user.service.impl;

import com.rmeunier.meowtopia.backend.shop.model.ShopItem;
import com.rmeunier.meowtopia.backend.user.exception.UserAccountNotFoundException;
import com.rmeunier.meowtopia.backend.user.exception.UserInventoryItemNotFoundException;
import com.rmeunier.meowtopia.backend.user.model.UserAccount;
import com.rmeunier.meowtopia.backend.user.model.UserInventoryItem;
import com.rmeunier.meowtopia.backend.user.repo.IUserAccountRepository;
import com.rmeunier.meowtopia.backend.user.repo.IUserInventoryItemRepository;
import com.rmeunier.meowtopia.backend.user.service.IUserInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserInventoryServiceImpl implements IUserInventoryService {
    private IUserAccountRepository userAccountRepository;
    private IUserInventoryItemRepository userInventoryItemRepository;

    @Autowired
    public UserInventoryServiceImpl(IUserAccountRepository userAccountRepository,
                                    IUserInventoryItemRepository userInventoryItemRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userInventoryItemRepository = userInventoryItemRepository;
    }

    @Override
    public UserInventoryItem addProductToInventory(UUID userAccountId, ShopItem shopItem, Integer quantity) {
        UserAccount userAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new UserAccountNotFoundException(userAccountId));

        UserInventoryItem inventoryItem = userInventoryItemRepository
                .findByUserAccountUserAccountIdAndProductProductId(userAccountId, shopItem.getProductId())
                .orElseGet(() -> UserInventoryItem.builder()
                        .userAccount(userAccount)
                        .product(shopItem)
                        .quantity(quantity)
                        .build());

        return userInventoryItemRepository.save(inventoryItem);
    }

    @Override
    public List<UserInventoryItem> getInventoryItemsByUser(UUID userAccountId) {
        return userInventoryItemRepository.findByUserAccountUserAccountId(userAccountId);
    }

    @Override
    public UserInventoryItem getInventoryItemById(UUID inventoryItemId) {
        return userInventoryItemRepository.findById(inventoryItemId)
                .orElseThrow(() -> new UserInventoryItemNotFoundException(inventoryItemId));
    }

    @Override
    public UserInventoryItem saveInventoryItem(UserInventoryItem inventoryItem) {
        return userInventoryItemRepository.save(inventoryItem);
    }

    @Override
    public boolean deleteInventoryItem(UUID inventoryItemId) {
        if (userInventoryItemRepository.existsById(inventoryItemId)) {
            userInventoryItemRepository.deleteById(inventoryItemId);
            return true;
        }
        return false;
    }
}
