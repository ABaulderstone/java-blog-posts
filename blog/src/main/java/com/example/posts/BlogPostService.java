package com.example.posts;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.posts.dtos.CreatePostDto;
import com.example.posts.repository.BlogPostRepository;

public class BlogPostService {
    private final BlogPostRepository repo;

    public BlogPostService(BlogPostRepository repo) {
        this.repo = repo;
    }

    public BlogPost createBlogPost(CreatePostDto data) {
        BlogPost newPost = new BlogPost();
        newPost.setCategory(data.getCategory());
        newPost.setTitle(data.getTitle().trim());
        newPost.setText(data.getText().trim());
        newPost.setCreatedAt(new Date());
        return this.repo.save(newPost);
    }

    public List<BlogPost> getAllPosts() {
        return this.repo.findAll();
    }

    public Optional<BlogPost> findById(Integer id) {
        return this.repo.findById(id);
    }

}
