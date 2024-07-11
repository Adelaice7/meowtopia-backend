package com.rmeunier.catworld.user.api;

import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserAccountApi {
    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountApi(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        return ResponseEntity.ok(userAccountService.getAllUserAccounts());
    }

    @GetMapping("/{userAccountId}")
    public ResponseEntity<UserAccount> getUserById(UUID userAccountId) {
        return ResponseEntity.ok(userAccountService.getUserAccountById(userAccountId));
    }

    @PutMapping
    public ResponseEntity<UserAccount> createUserAccount(UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.createUserAccount(userAccount));
    }

    @PatchMapping("/{userAccountId}")
    public ResponseEntity<UserAccount> updateUserAccount(UUID userAccountId, UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.updateUserAccount(userAccountId, userAccount));
    }

    @DeleteMapping("/{userAccountId}")
    public ResponseEntity<Void> deleteUserAccount(UUID userAccountId) {
        userAccountService.deleteUserAccount(userAccountId);
        return ResponseEntity.noContent().build();
    }
}
