package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserProfile;

import java.util.UUID;

public interface UserProfileService {

    UserProfile getUserProfileById(UUID userProfileId);

    UserProfile createUserProfileForUserAccount(UUID userAccountId, UserProfile userProfile);

    UserProfile updateUserProfile(UUID userProfileId, UserProfile userProfile);

}
