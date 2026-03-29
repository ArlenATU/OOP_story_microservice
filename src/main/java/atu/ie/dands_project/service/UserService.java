package atu.ie.dands_project.service;

import atu.ie.dands_project.model.Profile;
import atu.ie.dands_project.model.User;
import atu.ie.dands_project.model.StarStory;
import atu.ie.dands_project.repository.ProfileRepository;
import atu.ie.dands_project.repository.UserRepository;
import atu.ie.dands_project.repository.StarStoryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final StarStoryRepository starStoryRepository;

    public User register(User user) {

        User savedUser = userRepository.save(user);

        String avatarUrl = "https://i.pravatar.cc/150?u=" + savedUser.getId();

        Profile profile = new Profile();
        profile.setUser(savedUser);
        profile.setName(user.getUsername());
        profile.setBio("");
        profile.setLocation("");
        profile.setProfilePictureUrl(avatarUrl);

        profileRepository.save(profile);

        return savedUser;
    }

    public String login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return "Login successful";
    }

    // ✅ SAFE PROFILE RETURN
    public Map<String, Object> getProfile(Long userId) {

        Profile profile = profileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        List<StarStory> stories = starStoryRepository.findByUser_Id(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("profile", profile);
        response.put("stories", stories != null ? stories : new ArrayList<>());

        return response;
    }

    public Profile updateProfile(Long userId, Profile profile) {

        Profile existingProfile = profileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setName(profile.getName());
        existingProfile.setBio(profile.getBio());
        existingProfile.setLocation(profile.getLocation());
        existingProfile.setProfilePictureUrl(profile.getProfilePictureUrl());

        return profileRepository.save(existingProfile);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}