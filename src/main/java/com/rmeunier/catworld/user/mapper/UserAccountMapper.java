package com.rmeunier.catworld.user.mapper;

import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.model.dto.UserAccountDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccountMapper {

    public static UserAccountDto mapToDto(UserAccount userAccount) {
        return UserAccountDto.builder()
                .email(userAccount.getEmail())
                .password(userAccount.getPasswordHash())
                .username(userAccount.getUsername())
                .build();
    }

    public static UserAccount mapToEntity(UserAccountDto userAccountDto) {
        return UserAccount.builder()
                .email(userAccountDto.getEmail())
                .passwordHash(userAccountDto.getPassword())
                .username(userAccountDto.getUsername())
                .build();
    }

    public static UserDetails mapToUserDetails(UserAccount userAccount) {
        return User.withUsername(userAccount.getUsername())
                .password(userAccount.getPasswordHash())
                .authorities(userAccount.getAuthorities())
                .build();
    }
}
