package com.example.cli;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.jline.reader.LineReader;

import com.example.posts.BlogPost;
import com.example.posts.BlogPostService;
import com.example.posts.Category;
import com.example.posts.dtos.CreatePostDto;
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
                listPosts();
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
        CreatePostDto data = new CreatePostDto();
        System.out.println("Select a category");
        try {
            Category selectedCategory = helper.selectItemFromList(Arrays.asList(Category.values()));
            data.setCategory(selectedCategory);
            helper.clearScreen();
        } catch (IllegalArgumentException e) {
            CliHelper.printErrorMessage("Categories list empty");
        }
        System.out.println("Blog category selected");
        helper.pause();
        String title = helper.validatedInput("Enter blog title", s -> !s.isBlank(), "Title cannot be empty");
        data.setTitle(title);
        helper.clearScreen();
        System.out.println("Blog title created");
        helper.pause();
        String text = helper.validatedInput("Enter blog text", s -> !s.isBlank(), "Blog text cannot be empty");
        data.setText(text);
        service.createBlogPost(data);
        helper.clearScreen();
        System.out.println("Blog Post created");
        helper.pause();
    }

    public void listPosts() {
        System.out.println("Blog Posts");
        try {
            var selectedPost = helper.selectItemFromList(service.getAllPosts());
            System.out.println(selectedPost);
            helper.pause();
        } catch (IllegalArgumentException e) {
            helper.clearScreen();
            CliHelper.printErrorMessage("Blog Posts list empty");
            helper.pause();
        }
    }

}
