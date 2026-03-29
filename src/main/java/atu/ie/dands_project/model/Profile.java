package atu.ie.dands_project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    private String location;

    private String profilePictureUrl;

    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}