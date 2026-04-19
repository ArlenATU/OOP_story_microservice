package atu.ie.storyservice.service;

import atu.ie.storyservice.client.UserClient;
import atu.ie.storyservice.model.StarStory;
import atu.ie.storyservice.model.UserDTO;
import atu.ie.storyservice.repository.StarStoryRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StarStoryService {

    private final StarStoryRepository starStoryRepository;
    private final UserClient userClient;

    public StarStory createStory(StarStory storyDetails) {
        try {
            // Check if user exists
            UserDTO user = userClient.getUserById(storyDetails.getUserId());
            System.out.println("Success! Creating story for verified user: " + user.getUsername());

            return starStoryRepository.save(storyDetails);
        } catch (FeignException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User ID does not exist.");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "User service is temporarily unavailable.");
        }
    }

    public List<StarStory> getAllStories() {
        return starStoryRepository.findAll();
    }

    public List<StarStory> getUserStories(Long userId) {
        return starStoryRepository.findByUserId(userId);
    }

    public StarStory updateStory(Long storyId, StarStory updatedStory) {
        StarStory existingStory = starStoryRepository.findById(storyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Story not found"));

        existingStory.setTitle(updatedStory.getTitle());
        existingStory.setContent(updatedStory.getContent());
        return starStoryRepository.save(existingStory);
    }

    public void deleteStory(Long storyId) {
        starStoryRepository.deleteById(storyId);
    }
}