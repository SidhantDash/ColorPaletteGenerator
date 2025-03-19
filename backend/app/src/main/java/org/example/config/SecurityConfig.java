package org.example.config; // Change this to your actual package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

// @Configuration annotation indicates that this class contains Spring configuration.
// It tells Spring that this class will define beans to be managed by the Spring container.
@Configuration
// @EnableMethodSecurity enables security annotations for methods like
// @PreAuthorize or @Secured.
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Bean to encode passwords securely using BCrypt hashing algorithm.
     * This encoder will be used throughout the application to encode and validate
     * passwords.
     * 
     * @return PasswordEncoder implementation (BCryptPasswordEncoder)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean to configure CORS settings.
     * Cross-Origin Resource Sharing (CORS) allows the frontend (on a different
     * domain) to access
     * resources from the backend.
     * 
     * @return CorsConfigurationSource with custom settings.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // Create a new CorsConfiguration instance to define custom CORS settings
        CorsConfiguration configuration = new CorsConfiguration();
        // Define which origins are allowed to make requests (here, localhost:3000 is
        // allowed)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        // Set the allowed HTTP methods (GET, POST, PUT, DELETE, OPTIONS)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        // Set the allowed headers in the request (Authorization, Content-Type, and
        // Accept headers are allowed)
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        // Allow credentials (cookies, authentication headers) to be sent in CORS
        // requests
        configuration.setAllowCredentials(true);

        // Register the configuration with the CorsConfigurationSource to apply it to
        // all endpoints
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply CORS settings to all paths
        return source; // Return the configured CORS source
    }
}