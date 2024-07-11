package com.rmeunier.catworld.user.service;

import com.rmeunier.catworld.user.model.UserProfile;
import com.rmeunier.catworld.user.repo.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile getUserProfileById(UUID userProfileId) {
        return null;
    }

    @Override
    public UserProfile createUserProfile(UserProfile userProfile) {
        return null;
    }

    @Override
    public UserProfile updateUserProfile(UserProfile userProfile) {
        return null;
    }

    @Override
    public void deleteUserProfile(UUID userProfileId) {

    }
}
