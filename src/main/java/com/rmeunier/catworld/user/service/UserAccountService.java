package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.dto.UserAccountDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

    List<UserAccountDto> getAllUserAccounts();

    Page<UserAccountDto> getFilteredUserAccounts(Integer page, Integer size, String orderBy, String direction);

    UserAccountDto getUserAccountById(UUID userAccountId);

    UserAccountDto getUserAccountByUsername(String username);

    UserAccountDto createUserAccount(UserAccountDto userAccountDto);

    UserAccountDto updateUserAccount(UUID userAccountId, UserAccountDto userAccountDto);

    void deleteUserAccount(UUID userAccountId);
}
