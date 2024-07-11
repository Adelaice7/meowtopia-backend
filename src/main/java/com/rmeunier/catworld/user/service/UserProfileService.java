package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserProfile;

import java.util.UUID;

public interface UserProfileService {
    // Add methods for user profile management
    // Example: getProfileById, updateProfile, deleteProfile
    UserProfile getUserProfileById(UUID userProfileId);

    UserProfile createUserProfile(UserProfile userProfile);

    UserProfile updateUserProfile(UserProfile userProfile);

    void deleteUserProfile(UUID userProfileId);
}
