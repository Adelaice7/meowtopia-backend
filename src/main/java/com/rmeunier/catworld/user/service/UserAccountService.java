package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserAccount;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccount> getAllUserAccounts();

    UserAccount getUserAccountById(UUID userAccountId);

    UserAccount getUserAccountByUsername(String username);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(UUID userAccountId, UserAccount userAccount);

    void deleteUserAccount(UUID userAccountId);
}
