package atu.ie.storyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class StoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoryServiceApplication.class, args);
    }
}