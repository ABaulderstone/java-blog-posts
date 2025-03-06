package com.example.posts;

import java.util.Date;

import com.example.cli.CliDisplayable;

public class BlogPost implements CliDisplayable {

    private Integer id;
    private String title;
    private String text;
    private Date createdAt;
    private Category category;

    public BlogPost() {
    }

    public BlogPost(String title, String text) {
        this.title = title;
        this.text = text;
        this.createdAt = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toCliDisplay() {
        return this.title;
    }

}
