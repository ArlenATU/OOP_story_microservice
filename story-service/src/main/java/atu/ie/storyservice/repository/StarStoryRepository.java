package atu.ie.storyservice.repository;

import atu.ie.storyservice.model.StarStory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StarStoryRepository extends JpaRepository<StarStory, Long> {
    List<StarStory> findByUserId(Long userId);
}