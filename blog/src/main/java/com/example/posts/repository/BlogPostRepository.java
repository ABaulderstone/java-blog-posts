package com.example.posts.repository;

import java.util.List;
import java.util.Optional;

import com.example.posts.BlogPost;

public interface BlogPostRepository {
    public BlogPost save(BlogPost post);

    public Optional<BlogPost> findById(Integer id);

    public List<BlogPost> findAll();

}
