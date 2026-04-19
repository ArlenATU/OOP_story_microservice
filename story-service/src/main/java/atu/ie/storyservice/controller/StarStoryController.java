package atu.ie.storyservice.controller;

import atu.ie.storyservice.model.StarStory;
import atu.ie.storyservice.service.StarStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
public class StarStoryController {

    private final StarStoryService starStoryService;

    @PostMapping
    public ResponseEntity<StarStory> createStory(@RequestBody StarStory storyDetails) {
        StarStory createdStory = starStoryService.createStory(storyDetails);
        return ResponseEntity.ok(createdStory);
    }

    @GetMapping
    public ResponseEntity<List<StarStory>> getAllStories() {
        return ResponseEntity.ok(starStoryService.getAllStories());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StarStory>> getUserStories(@PathVariable Long userId) {
        return ResponseEntity.ok(starStoryService.getUserStories(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StarStory> updateStory(@PathVariable Long id, @RequestBody StarStory updatedStory) {
        return ResponseEntity.ok(starStoryService.updateStory(id, updatedStory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable Long id) {
        starStoryService.deleteStory(id);
        return ResponseEntity.noContent().build();
    }
}