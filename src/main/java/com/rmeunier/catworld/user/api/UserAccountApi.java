package com.rmeunier.catworld.user.api;

import com.rmeunier.catworld.user.model.UserAccount;
import com.rmeunier.catworld.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/filtered")
    public ResponseEntity<Page<UserAccount>> getFilteredUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                              @RequestParam(value = "orderBy", defaultValue = "lastName") String orderBy,
                                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok(userAccountService.getFilteredUserAccounts(page, size, orderBy, direction));
    }

    @GetMapping("/{userAccountId}")
    public ResponseEntity<UserAccount> getUserById(@PathVariable("userAccountId") UUID userAccountId) {
        return ResponseEntity.ok(userAccountService.getUserAccountById(userAccountId));
    }

    @PutMapping
    public ResponseEntity<UserAccount> createUserAccount(UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.createUserAccount(userAccount));
    }

    @PatchMapping("/{userAccountId}")
    public ResponseEntity<UserAccount> updateUserAccount(@PathVariable("userAccountId") UUID userAccountId, UserAccount userAccount) {
        return ResponseEntity.ok(userAccountService.updateUserAccount(userAccountId, userAccount));
    }

    @DeleteMapping("/{userAccountId}")
    public ResponseEntity<Void> deleteUserAccount(@PathVariable("userAccountId") UUID userAccountId) {
        userAccountService.deleteUserAccount(userAccountId);
        return ResponseEntity.noContent().build();
    }
}
