package com.rmeunier.catworld.user.api;

import com.rmeunier.catworld.user.model.UserProfile;
import com.rmeunier.catworld.user.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/profiles")
public class UserProfileApi {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileApi(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/{userProfileId}")
    public ResponseEntity<UserProfile> getUserProfileById(@PathVariable("userProfileId") UUID userProfileId) {
        return ResponseEntity.ok(userProfileService.getUserProfileById(userProfileId));
    }
}
