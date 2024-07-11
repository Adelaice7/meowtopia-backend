package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.exception.UserAccountNotFoundException;
import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public Page<UserAccount> getFilteredUserAccounts(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return userAccountRepository.findAll(pageRequest);
    }

    @Override
    public UserAccount getUserAccountById(UUID userAccountId) {
        return userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new UserAccountNotFoundException(userAccountId));
    }

    @Override
    public UserAccount getUserAccountByUsername(String username) {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UserAccountNotFoundException("Username not found: " + username));
    }

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        // TODO validation
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount updateUserAccount(UUID userAccountId, UserAccount userAccount) {

        UserAccount existitngUserAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new UserAccountNotFoundException(userAccountId));

        existitngUserAccount.setUserProfile(userAccount.getUserProfile());
        // TODO fix this
        return userAccountRepository.save(existitngUserAccount);
    }

    @Override
    public void deleteUserAccount(UUID userAccountId) {
        if (userAccountRepository.existsById(userAccountId)) {
            userAccountRepository.deleteById(userAccountId);
        } else {
            throw new UserAccountNotFoundException(userAccountId);
        }
    }
}
