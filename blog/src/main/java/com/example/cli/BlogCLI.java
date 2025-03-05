package com.example.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.jline.reader.LineReader;

import com.example.posts.BlogPost;
import com.example.posts.BlogPostService;
import com.example.posts.Category;
import com.example.posts.repository.BlogPostRepository;
import com.example.posts.repository.InMemoryRepository;

public class BlogCLI {
    private final BlogPostRepository repo;
    private final BlogPostService service;
    private final CliHelper helper;

    public BlogCLI() throws IOException {
        this.repo = new InMemoryRepository();
        this.service = new BlogPostService(repo);
        this.helper = new CliHelper();
    }

    public void start() {
        System.out.println("Welcome to CLI Blogger");
        String input = helper.validateIntInRange(
                "Choose from the following options\n 1. Create Post\n 2. List Posts \n 3. Exit", 1, 3);
        switch (input) {
            case "1":
                createPost();
                break;
            case "2":
                System.out.println("List");
                break;
            case "3":
                System.out.println("Exiting...");
                helper.close();
                return;
            default:
                System.out.println("Invalid Input");
        }
        start();

    }

    public void createPost() {
        helper.clearScreen();
        System.out.println("Select a category");
        var selected = helper.selectItemFromList(Arrays.asList(Category.values()));
        System.out.println(selected);
        helper.pause();

    }

}
