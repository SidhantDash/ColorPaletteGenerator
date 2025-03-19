package org.example.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation that generates getters, setters, toString, equals, and
      // hashCode methods.
@NoArgsConstructor // Lombok annotation that generates a no-argument constructor.
@AllArgsConstructor // Lombok annotation that generates an all-argument constructor (one that takes
                    // all fields).
public class LoginRequest {
    // Validation annotation to ensure the username field is not blank (null or only
    // whitespace)
    // The message specifies the error message to be used if validation fails
    // There are many other validation annotations, however NotBlank is the only one
    // used here.
    @NotBlank(message = "Username or email is required and must not be blank")
    private String username;
    @NotBlank(message = "Password is required and must not be blank")
    private String password;
}