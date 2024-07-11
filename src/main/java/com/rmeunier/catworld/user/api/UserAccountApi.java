package com.rmeunier.catworld.user.api;

import com.rmeunier.catworld.other.GenericResponse;
import com.rmeunier.catworld.user.model.dto.UserAccountDto;
import com.rmeunier.catworld.user.service.IUserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@Validated
public class UserAccountApi {
    private final IUserAccountService userAccountService;

    @Autowired
    public UserAccountApi(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<Page<UserAccountDto>> getFilteredUsers(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                              @RequestParam(value = "size", defaultValue = "3") Integer size,
                                                              @RequestParam(value = "orderBy", defaultValue = "lastName") String orderBy,
                                                              @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok(userAccountService.getFilteredUserAccounts(page, size, orderBy, direction));
    }

    @GetMapping("/{userAccountId}")
    public ResponseEntity<UserAccountDto> getUserAccountById(@PathVariable("userAccountId") UUID userAccountId) {
        return ResponseEntity.ok(userAccountService.getUserAccountById(userAccountId));
    }

    @PostMapping
    public ResponseEntity<UserAccountDto> signUpAndCreateUserAccount(@RequestBody @Valid UserAccountDto userAccountDto) {
        return ResponseEntity.ok(userAccountService.createUserAccount(userAccountDto));
    }

    @PatchMapping("/{userAccountId}")
    public ResponseEntity<UserAccountDto> updateUserAccount(@PathVariable("userAccountId") UUID userAccountId, @Valid  UserAccountDto userAccountDto) {
        return ResponseEntity.ok(userAccountService.updateUserAccount(userAccountId, userAccountDto));
    }

    @DeleteMapping("/{userAccountId}")
    public ResponseEntity<UserAccountDto> deleteUserAccount(@PathVariable("userAccountId") UUID userAccountId) {
        userAccountService.deleteUserAccount(userAccountId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse handlePasswordValidationException(MethodArgumentNotValidException e) {

        return GenericResponse.builder()
                .message(String.join(",", e.getMessage()))
                .timestamp(Instant.now().getEpochSecond())
                .build();

    }
}
