package com.rmeunier.meowtopia.backend.user.service;

import com.rmeunier.meowtopia.backend.cat.model.Cat;
import com.rmeunier.meowtopia.backend.user.model.dto.UserAccountDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface IUserAccountService {

    List<UserAccountDto> getAllUserAccounts();

    Page<UserAccountDto> getFilteredUserAccounts(Integer page, Integer size, String orderBy, String direction);

    UserAccountDto getUserAccountById(UUID userAccountId);

    List<Cat> getAllCatsByUserAccountId(UUID userAccountId);

    UserAccountDto getUserAccountByUsername(String username);

    UserAccountDto createUserAccount(UserAccountDto userAccountDto);

    UserAccountDto updateUserAccount(UUID userAccountId, UserAccountDto userAccountDto);

    boolean deleteUserAccount(UUID userAccountId);
}
