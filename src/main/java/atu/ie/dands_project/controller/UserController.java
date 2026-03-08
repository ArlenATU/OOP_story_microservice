package atu.ie.dands_project.controller;

// Importing the Profile entity
import atu.ie.dands_project.model.Profile;

// Importing the User entity
import atu.ie.dands_project.model.User;

// Importing the service layer
import atu.ie.dands_project.service.UserService;

// Lombok annotation to generate constructor for final fields
import lombok.RequiredArgsConstructor;

// Spring Web annotations
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @RestController
 * Marks this class as a REST controller.
 * It automatically converts return values into JSON.
 */
@RestController

/*
 * @RequestMapping("/api/users")
 * Sets the base URL for all endpoints in this controller.
 * Every endpoint will start with /api/users
 */
@RequestMapping("/api/users")

/*
 * @RequiredArgsConstructor
 * Lombok generates a constructor for all final fields.
 * This enables dependency injection for userService.
 */
@RequiredArgsConstructor
public class UserController {

    /*
     * Service layer dependency.
     * Spring automatically injects it using constructor injection.
     */
    private final UserService userService;

    /*
     * @PostMapping("/register")
     * Handles POST requests to /api/users/register
     * Used to register a new user.
     */
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        /*
         * @RequestBody converts incoming JSON into a User object.
         * Then we delegate logic to the service layer.
         */
        return userService.register(user);
    }

    /*
     * @PostMapping("/login")
     * Handles POST requests to /api/users/login
     * Used for user login.
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        /*
         * Extracts email and password from request body
         * and passes them to the service for authentication.
         */
        return userService.login(user.getEmail(), user.getPassword());
    }

    /*
     * @GetMapping("/profile/{userId}")
     * Handles GET requests to /api/users/profile/{userId}
     * Returns profile information for a specific user.
     */
    @GetMapping("/profile/{userId}")
    public Profile getProfile(@PathVariable Long userId) {

        /*
         * @PathVariable extracts the userId from the URL.
         * Example: /api/users/profile/1 → userId = 1
         */
        return userService.getProfile(userId);
    }

    /*
     * @PutMapping("/profile/{userId}")
     * Handles PUT requests to update user profile.
     */
    @PutMapping("/profile/{userId}")
    public Profile updateProfile(@PathVariable Long userId,
                                 @RequestBody Profile profile) {

        /*
         * Takes updated profile data from request body
         * and passes it to the service layer.
         */
        return userService.updateProfile(userId, profile);
    }

    /*
     * @GetMapping("/all")
     * Handles GET requests to /api/users/all
     * Returns a list of all registered users.
     */
    @GetMapping("/all")
    public List<User> getAllUsers() {

        /*
         * Calls service layer to fetch all users from database.
         */
        return userService.getAllUsers();
    }
}
