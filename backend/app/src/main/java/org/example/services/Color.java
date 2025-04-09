package org.example.services;

public class Color {

    public static String generateRandomColor() {
        int r = (int) Math.random() * 255;
        int g = (int) Math.random() * 255;
        int b = (int) Math.random() * 255;

        return String.format("#%02x%02x%02x", r, g, b);
    }
}
