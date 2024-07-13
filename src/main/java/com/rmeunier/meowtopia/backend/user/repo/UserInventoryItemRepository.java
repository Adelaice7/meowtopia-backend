package com.rmeunier.meowtopia.backend.user.repo;

import com.rmeunier.meowtopia.backend.user.model.UserInventoryItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInventoryItemRepository extends JpaRepository<UserInventoryItem, UUID> {
    Page<UserInventoryItem> findByUserAccountUserAccountId(UUID userAccountId, org.springframework.data.domain.Pageable pageable);
}
