package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main entry point for the Spring Boot application.
 * This class starts the embedded web server and initializes the application
 * environment.
 */
@SpringBootApplication // Makes this a spring boot application
@EnableAsync // Allows async processing
@EnableJpaRepositories // Enables JPA repository support, allowing Spring Data JPA to manage database
                       // repositories
public class SimpleHttpServerApplication {
    /**
     * Main method to launch the Spring Boot application.
     * 
     * @param args Command-line arguments (if any)
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleHttpServerApplication.class, args); // Starts the Spring Boot application
    }
}
