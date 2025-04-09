package org.example.models;

import org.checkerframework.checker.units.qual.min;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation that generates getters, setters, toString, equals, and
      // hashCode methods.
@NoArgsConstructor // Lombok annotation that generates a no-argument constructor.
@AllArgsConstructor // Lombok annotation that generates an all-argument constructor (one that takes
                    // all fields).
public class ColorRequest {
    
    @Min(value = 0) @Max(value = 255)
    private int r;
    @Min(value = 0) @Max(value = 255)
    private int g;
    @Min(value = 0) @Max(value = 255)
    private int b;
    private String hexCode;

}
