package org.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.example.tables.Users;
import org.example.models.LoginRequest;
import org.example.models.SignupRequest;
import org.example.repositories.UserRepository;

import java.util.*;

import javax.validation.Valid;

// @RestController annotation indicates that this class is a RESTful controller
// which handles HTTP requests and responses, and automatically serializes the return value into JSON.
// Since this is a controller, and ErrorController has ControllerAdvice, we do not need to handle exceptions here. That is why none of the endpoints have a try catch like normal.
@RestController
// @RequestMapping("/api") maps the controller's methods to a specific URL
// pattern. In this case,
// the controller will handle any requests starting with "/api".
@RequestMapping("/api")
// @Validated annotation is used to trigger validation of method parameters
// (such as @NotNull, @Size, etc.)
// before the controller methods are executed. It ensures that the input data
// meets validation constraints.
@Validated
public class ApiController {

    // Dependency injection for the UserRepository, which allows access to the user
    // data in the database.
    // The repository provides methods for CRUD operations related to the "User"
    // entity.
    private final UserRepository userRepository;
    // Dependency injection for the PasswordEncoder, which is used to securely hash
    // or encode passwords.
    private final PasswordEncoder passwordEncoder;

    // Secret key used for encoding or hashing.
    // It should not be hardcoded in production for security reasons (e.g., stored
    // in environment variables or a configuration file).
    private static final String SECRET_KEY = "your_fixed_256_bit_base64_encoded_key_here"; // should NOT be hard coded

    // Token expiration time, set to 1 hour (in milliseconds). This is the duration
    // for which the authentication token is valid.
    private static final int EXPIRATION_TIME = 1000 * 60 * 60;

    // Constructor-based dependency injection. The constructor is used to inject the
    // dependencies (UserRepository and PasswordEncoder)
    // into the controller. This makes it easier to write unit tests for the
    // controller since the dependencies are provided at runtime.
    @Autowired
    public ApiController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/secureEndpoint")
    public ResponseEntity<String> secureEndpoint(HttpServletRequest request, HttpServletResponse response) {
        // 1. Extract the JWT token from the cookie
        String jwtToken = extractJwtToken(request);

        // 2. Validate the JWT token
        if (jwtToken == null || !validateJwtToken(jwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }

        // 3. If the token is valid, proceed with the protected logic
        // ... perform your protected logic here ...
        return ResponseEntity.ok("Secure endpoint accessed successfully");
    }

    /**
     * User signup endpoint to register a new user.
     */
    @PostMapping("/signup") // Maps HTTP POST requests to this method for the "/signup" URL path
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequest signupRequest) {
        // Check if the username already exists in the database by searching the user
        // repository.
        // If the username already exists, return a Conflict (409) status with an error
        // message.
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Create a new Users object with the data from the signupRequest object.
        // The password is encoded using the passwordEncoder, appending the SECRET_KEY
        // to enhance security.
        Users user = new Users(
                signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword() + SECRET_KEY),
                signupRequest.getEmail());

        // Save the newly created user to the database via the userRepository
        userRepository.save(user);

        // Return a successful response with a "User registered successfully" message
        // and a Created (201) status to indicate that a new user has been created.
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    /**
     * User login endpoint for authentication and token generation.
     */
    @PostMapping("/login") // Maps HTTP POST requests to this method for the "/login" URL path
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest, HttpServletResponse response) {
        // Try to find the user by username in the user repository.
        // If the user is not found, throw a RuntimeException with the message "User not
        // found".
        Users user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the provided password (with SECRET_KEY appended) matches the stored
        // password (encoded).
        if (passwordEncoder.matches(loginRequest.getPassword() + SECRET_KEY, user.getPassword())) {
            // If the password matches, generate a token (usually a JWT) for authentication
            // and authorization.
            String token = generateToken(user);

            // Create a secure, HttpOnly cookie containing the JWT token
            Cookie jwtCookie = new Cookie("jwtToken", token);
            jwtCookie.setHttpOnly(true); // Prevents JavaScript access to the cookie
            jwtCookie.setSecure(true); // Sends cookie only over HTTPS
            jwtCookie.setPath("/"); // Makes cookie available for all paths
            jwtCookie.setMaxAge(EXPIRATION_TIME / 1000); // Convert milliseconds to seconds for cookie expiration

            // Add the cookie to the response
            response.addCookie(jwtCookie);

            // Return success response without exposing the token in the body
            return ResponseEntity.ok("Login successful");
        } else {
            // If the password doesn't match, return an Unauthorized (401) status with a
            // message.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /**
     * Token generation method for authenticated users.
     * This method creates a JWT (JSON Web Token) that contains user information and
     * other claims,
     * signs it with a secret key, and returns the token as a string.
     */
    private String generateToken(Users user) {
        return Jwts.builder()
                .issuer("me") // Set the issuer of the token, typically the name of the service or app
                              // generating it
                .subject(user.getUsername()) // Set the subject of the token (the user), typically used for identifying
                                             // the user
                .claim("Some Name", "Some Value") // Custom claim to add extra information to the token. Can be used for
                                                  // storing additional metadata.
                .claim("Some Name 2", "Some Value") // Custom claim to add extra information to the token. Can be used
                                                    // for storing additional metadata.
                .issuedAt(new Date(System.currentTimeMillis())) // Set the issued date of the token to the current time
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) // Sign the token with HMAC-SHA256 by default
                .compact(); // Finalize and return JWT string
    }

    private String extractJwtToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null; // Token not found
    }

    private boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}