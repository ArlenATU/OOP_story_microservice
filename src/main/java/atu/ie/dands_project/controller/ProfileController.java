package atu.ie.dands_project.controller;

import atu.ie.dands_project.model.Profile;
import atu.ie.dands_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public Map<String, Object> getProfile(@PathVariable Long userId){
        return userService.getProfile(userId);
    }

    @PutMapping("/{userId}")
    public Profile updateProfile(@PathVariable Long userId,
                                 @RequestBody Profile profile){
        return userService.updateProfile(userId, profile);
    }
}