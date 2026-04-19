package atu.ie.storyservice.controller;

import atu.ie.storyservice.model.StarStory;
import atu.ie.storyservice.service.StarStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StarStoryController {

    private final StarStoryService starStoryService;

    @PostMapping
    public StarStory createStory(@RequestBody StarStory story) {
        return starStoryService.createStory(story);
    }

    @GetMapping
    public List<StarStory> getAllStories() {
        return starStoryService.getAllStories();
    }

    @GetMapping("/user/{userId}")
    public List<StarStory> getUserStories(@PathVariable Long userId) {
        return starStoryService.getUserStories(userId);
    }

    @PutMapping("/{id}")
    public StarStory updateStory(@PathVariable Long id, @RequestBody StarStory story) {
        return starStoryService.updateStory(id, story);
    }

    @DeleteMapping("/{id}")
    public void deleteStory(@PathVariable Long id) {
        starStoryService.deleteStory(id);
    }
}
