package com.rmeunier.catworld.shop.service;

import com.rmeunier.catworld.shop.model.Transaction;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ITransactionService {
    Page<Transaction> getTransactionsByUser(UUID userAccountId, Integer size, Integer page, String orderBy, String direction);

    Transaction getTransactionById(UUID transactionId);

    Transaction createTransaction(Transaction transaction);

    boolean deleteTransaction(UUID transactionId);

}
