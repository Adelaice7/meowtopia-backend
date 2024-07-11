package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount getUserAccountById(UUID userAccountId) {
//        return userAccountRepository.getUserAccountById(userAccountId);
        return null;
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        return null;
    }

    @Override
    public UserAccount updateUserAccount(UserAccount userAccount) {
        return null;
    }

    @Override
    public void deleteUserAccount(UUID userAccountId) {

    }
}
