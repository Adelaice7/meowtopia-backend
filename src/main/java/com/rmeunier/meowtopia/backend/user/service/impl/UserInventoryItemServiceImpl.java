package com.rmeunier.meowtopia.backend.user.service.impl;

import com.rmeunier.meowtopia.backend.shop.model.Product;
import com.rmeunier.meowtopia.backend.user.exception.UserInventoryItemNotFoundException;
import com.rmeunier.meowtopia.backend.user.model.UserInventoryItem;
import com.rmeunier.meowtopia.backend.user.repo.IUserInventoryItemRepository;
import com.rmeunier.meowtopia.backend.user.service.IUserInventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInventoryItemServiceImpl implements IUserInventoryItemService {
    private IUserInventoryItemRepository userInventoryItemRepository;

    @Autowired
    public UserInventoryItemServiceImpl(IUserInventoryItemRepository userInventoryItemRepository) {
        this.userInventoryItemRepository = userInventoryItemRepository;
    }

    public void buyProduct(UUID userAccountId, Product product, int quantity) {
        // Add logic to handle inventory
        // Example: save new inventory item or update existing inventory
    }

    @Override
    public Page<UserInventoryItem> getInventoryItemsByUser(UUID userAccountId, Integer size, Integer page, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return userInventoryItemRepository.findByUserAccountUserAccountId(userAccountId, pageRequest);
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
