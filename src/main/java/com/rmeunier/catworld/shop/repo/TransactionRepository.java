package com.rmeunier.catworld.shop.repo;

import com.rmeunier.catworld.shop.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Page<Transaction> findByUserAccountUserAccountId(UUID userAccountId, Pageable pageable);
}
