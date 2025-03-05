package com.example.posts;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Category {
    TECHNOLOGY("technology"),
    LIFESTYLE("lifestyle"),
    ARTS("arts"),
    PERSONAL_DEVELOPMENT("personal development"),
    FOOD("food");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Category fromDisplayName(String input) {
        return Arrays.stream(values()).filter(c -> c.displayName.equalsIgnoreCase(input)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No category with display name " + input));
    }

    @Override
    public String toString() {
        return Arrays.stream(displayName.split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }

}