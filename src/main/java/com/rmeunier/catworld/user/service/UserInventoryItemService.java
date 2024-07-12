package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.exception.UserInventoryItemNotFoundException;
import com.rmeunier.catworld.user.model.UserInventoryItem;
import com.rmeunier.catworld.user.repo.UserInventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserInventoryItemService implements IUserInventoryItemService {
    private UserInventoryItemRepository userInventoryItemRepository;

    @Autowired
    public UserInventoryItemService(UserInventoryItemRepository userInventoryItemRepository) {
        this.userInventoryItemRepository = userInventoryItemRepository;
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
