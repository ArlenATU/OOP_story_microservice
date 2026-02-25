package atu.ie.dands_project.controller;

import atu.ie.dands_project.model.Profile;
import atu.ie.dands_project.model.User;
import atu.ie.dands_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST /register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // POST /login
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return userService.login(email, password);
    }

    // GET /profile/{userId}
    @GetMapping("/profile/{userId}")
    public Profile getProfile(@PathVariable Long userId) {
        return userService.getProfile(userId);
    }

    // PUT /profile/{userId}
    @PutMapping("/profile/{userId}")
    public Profile updateProfile(@PathVariable Long userId,
                                 @RequestBody Profile profile) {
        return userService.updateProfile(userId, profile);
    }

    // GET /all (optional)
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}