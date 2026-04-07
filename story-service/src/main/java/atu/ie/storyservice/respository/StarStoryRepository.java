package atu.ie.dands_project.repository;

import atu.ie.dands_project.model.StarStory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StarStoryRepository extends JpaRepository<StarStory, Long> {
    List<StarStory> findByUserId(Long userId);
}