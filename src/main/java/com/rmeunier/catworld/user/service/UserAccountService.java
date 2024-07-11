package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserAccount;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccount> getAllUserAccounts();

    Page<UserAccount> getFilteredUserAccounts(Integer page, Integer size, String orderBy, String direction);

    UserAccount getUserAccountById(UUID userAccountId);

    UserAccount getUserAccountByUsername(String username);

    UserAccount createUserAccount(UserAccount userAccount);

    UserAccount updateUserAccount(UUID userAccountId, UserAccount userAccount);

    void deleteUserAccount(UUID userAccountId);
}
