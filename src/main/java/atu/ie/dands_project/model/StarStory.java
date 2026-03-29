package atu.ie.dands_project.model;

import jakarta.persistence.*;

@Entity
public class StarStory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
