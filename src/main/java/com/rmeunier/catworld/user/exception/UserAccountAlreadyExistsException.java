package com.rmeunier.catworld.user.exception;

public class UserAccountAlreadyExistsException extends RuntimeException {
    public UserAccountAlreadyExistsException(String username) {
        super("User account with username " + username + " already exists");
    }
}
