package com.example.cli;

import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class CliHelper {
    private final Scanner s;

    public CliHelper() {
        this.s = new Scanner(System.in);
    }

    public void pause() {
        System.out.println(colorText("\nPress Enter to continue...", ConsoleColor.GREEN));
        s.nextLine();
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void close() {
        s.close();
    }

    public String validatedInput(String prompt, Predicate<String> validator, String errorMessage) {
        while (true) {
            System.out.println(colorText(prompt, ConsoleColor.YELLOW));
            String input = s.nextLine().trim();
            if (validator.test(input)) {
                return input;
            }
            clearScreen();
            System.out.println(colorText(errorMessage, ConsoleColor.BRIGHT_RED));

        }
    }

    public String validateInputList(String prompt, List<String> list) {
        String seperated = String.join(", ", list);
        return validatedInput(prompt, s -> list.contains(s), "Please selected from the following: " + seperated);
    }

    public String validateIntInRange(String prompt, int min, int max) {
        return validatedInput(prompt, s -> {
            try {
                int value = Integer.parseInt(s);
                return value >= min && value <= max;
            } catch (NumberFormatException e) {
                return false;
            }
        }, String.format("Please enter number between %s and %s", min, max));
    }

    public <T> T selectItemFromList(List<T> list) {
        var builder = new StringBuilder();
        builder.append("Select from: \n");
        for (int i = 0; i < list.size(); i++) {
            builder.append(String.format("%s. %s\n", i + 1, list.get(i)));
        }
        String prompt = builder.toString();
        String selected = validateIntInRange(prompt, 1, list.size());
        int index = Integer.parseInt(selected) - 1;
        return list.get(index);
    }

    public static String colorText(String text, ConsoleColor color) {
        return String.format("%s%s%s", color, text, ConsoleColor.RESET);
    }

    public static enum ConsoleColor {
        RESET("\033[0m"),
        BLACK("\033[30m"),
        RED("\033[31m"),
        GREEN("\033[32m"),
        YELLOW("\033[33m"),
        BLUE("\033[34m"),
        PURPLE("\033[35m"),
        CYAN("\033[36m"),
        WHITE("\033[37m"),
        BRIGHT_RED("\033[91m"),
        BRIGHT_GREEN("\033[92m"),
        BRIGHT_YELLOW("\033[93m"),
        BRIGHT_BLUE("\033[94m"),
        BRIGHT_PURPLE("\033[95m"),
        BRIGHT_CYAN("\033[96m"),
        BRIGHT_WHITE("\033[97m");

        private final String code;

        ConsoleColor(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

}
