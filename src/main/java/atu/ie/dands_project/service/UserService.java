package atu.ie.dands_project.service;

import atu.ie.dands_project.model.Profile;
import atu.ie.dands_project.model.User;
import atu.ie.dands_project.repository.ProfileRepository;
import atu.ie.dands_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    // Register user
    public User register(User user) {

        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        Profile profile = Profile.builder()
                .userId(savedUser.getId())
                .bio("")
                .location("")
                .profilePictureUrl("")
                .build();

        profileRepository.save(profile);

        return savedUser;
    }

    // Login
    public String login(String email, String password) {

        return userRepository.findByEmail(email)
                .map(user -> {
                    if (user.getPassword().equals(password)) {
                        return "Login successful";
                    } else {
                        return "Invalid password";
                    }
                })
                .orElse("User not found");
    }

    // Get profile
    public Profile getProfile(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    // Update profile
    public Profile updateProfile(Long userId, Profile updatedProfile) {

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        profile.setBio(updatedProfile.getBio());
        profile.setLocation(updatedProfile.getLocation());
        profile.setProfilePictureUrl(updatedProfile.getProfilePictureUrl());

        return profileRepository.save(profile);
    }

    // Get all users (optional)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
