package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.exception.UserAccountNotFoundException;
import com.rmeunier.catworld.user.mapper.UserAccountMapper;
import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.model.dto.UserAccountDto;
import com.rmeunier.catworld.user.repo.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserAccountDto> getAllUserAccounts() {
        return userAccountRepository.findAll().stream()
                .map(UserAccountMapper::mapToDto)
                .toList();
    }

    public Page<UserAccountDto> getFilteredUserAccounts(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return userAccountRepository.findAll(pageRequest)
                .map(UserAccountMapper::mapToDto);
    }

    @Override
    public UserAccountDto getUserAccountById(UUID userAccountId) {
        UserAccount userAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new UserAccountNotFoundException(userAccountId));
        return UserAccountMapper.mapToDto(userAccount);
    }

    @Override
    public UserAccountDto getUserAccountByUsername(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UserAccountNotFoundException("Username not found: " + username));
        return UserAccountMapper.mapToDto(userAccount);
    }

    @Override
    public UserAccountDto createUserAccount(UserAccountDto userAccountDto) {
        UserAccount userAccount = UserAccountMapper.mapToEntity(userAccountDto);
        userAccount.setPasswordHash(passwordEncoder.encode(userAccount.getPassword()));
        UserAccount save = userAccountRepository.save(userAccount);
        return UserAccountMapper.mapToDto(save);
    }

    @Override
    public UserAccountDto updateUserAccount(UUID userAccountId, UserAccountDto userAccountDto) {

        UserAccount existitngUserAccount = userAccountRepository.findById(userAccountId)
                .orElseThrow(() -> new UserAccountNotFoundException(userAccountId));

        UserAccount userAccount = UserAccountMapper.mapToEntity(userAccountDto);
        existitngUserAccount.setUserProfile(userAccount.getUserProfile());

        // TODO fix this
        UserAccount saved = userAccountRepository.save(existitngUserAccount);
        return UserAccountMapper.mapToDto(saved);
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
