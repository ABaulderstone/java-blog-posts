package com.example.posts.serializer;

import java.io.IOException;
import java.util.List;

import com.example.posts.BlogPost;
import com.example.posts.Category;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JacksonBlogPostSerializer implements BlogPostSerializer {
    private final ObjectMapper objectMapper;

    public JacksonBlogPostSerializer() {
        this.objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addSerializer(Category.class, new JsonSerializer<Category>() {
            @Override
            public void serialize(Category value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeString(value.getDisplayName());
            }
        });

        module.addDeserializer(Category.class, new JsonDeserializer<Category>() {

            @Override
            public Category deserialize(JsonParser p, DeserializationContext ctxt)
                    throws IOException, JacksonException {
                String displayName = p.getValueAsString();
                return Category.fromDisplayName(displayName);
            }
        });

        objectMapper.registerModule(module);
    }

    @Override
    public String serialize(BlogPost post) throws SerializationException {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(post);
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize post", e);
        }
    }

    @Override
    public String serializeList(List<BlogPost> posts) throws SerializationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'serializeList'");
    }

    @Override
    public BlogPost deserialize(String json) throws SerializationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
    }

    @Override
    public List<BlogPost> deserializeList(String json) throws SerializationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deserializeList'");
    }

}
