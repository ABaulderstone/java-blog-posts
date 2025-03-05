package com.example.posts.serializer;

import java.util.List;

import com.example.posts.BlogPost;

public interface BlogPostSerializer {
    String serialize(BlogPost post) throws SerializationException;

    String serializeList(List<BlogPost> posts) throws SerializationException;

    BlogPost deserialize(String json) throws SerializationException;

    List<BlogPost> deserializeList(String json) throws SerializationException;

}
