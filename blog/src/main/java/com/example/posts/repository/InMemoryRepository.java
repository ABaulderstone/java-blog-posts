package com.example.posts.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.posts.BlogPost;

public class InMemoryRepository implements BlogPostRepository {
    private int count;
    private ArrayList<BlogPost> posts;

    public InMemoryRepository() {
        this.count = 0;
        this.posts = new ArrayList<BlogPost>();
    }

    @Override
    public BlogPost save(BlogPost post) {
        post.setId(++count);
        this.posts.add(post);
        return post;
    }

    @Override
    public Optional<BlogPost> findById(Integer id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<BlogPost> findAll() {
        return new ArrayList<>(this.posts);
    }

}
