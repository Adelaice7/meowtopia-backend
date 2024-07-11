package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserAccount;

import java.util.UUID;

public interface UserAccountService {
    // Add methods for user account management
    // Example: getAccountById, updateAccount, deleteAccount
    UserAccount getUserAccountById(UUID userAccountId);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(UserAccount userAccount);

    void deleteUserAccount(UUID userAccountId);
}
