package com.tanvir.blogpost.controller;

import com.tanvir.blogpost.dto.BlogPostDTO;
import com.tanvir.blogpost.service.BlogPostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogPostController.class)
public class BlogPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BlogPostService blogPostService;

    @Test
    public void testCreateBlogPost() throws Exception {
        // Arrange
        String blogPostJson = "{\"title\": \"Test Title\", \"content\": \"Test Content\", \"author\": \"Test Author\"}";
        Mockito.when(blogPostService.createBlogPost(Mockito.any())).thenReturn(new BlogPostDTO(1L, "Test Title", "Test Content", "Test Author"));

        // Act and Assert
        mockMvc.perform(post("/api/blogposts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(blogPostJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.content").value("Test Content"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void testGetAllBlogPosts() throws Exception {
        BlogPostDTO blogPost1 = new BlogPostDTO(1L, "Title 1", "Content 1", "Author 1");
        BlogPostDTO blogPost2 = new BlogPostDTO(2L, "Title 2", "Content 2", "Author 2");
        Mockito.when(blogPostService.getAllBlogPosts()).thenReturn(Arrays.asList(blogPost1, blogPost2));

        mockMvc.perform(get("/api/blogposts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Title 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Title 2"));
    }

    @Test
    public void testGetBlogPostById() throws Exception {
        BlogPostDTO blogPost = new BlogPostDTO(1L, "Title 1", "Content 1", "Author 1");
        Mockito.when(blogPostService.getBlogPostById(1L)).thenReturn(blogPost);

        mockMvc.perform(get("/api/blogposts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title 1"))
                .andExpect(jsonPath("$.content").value("Content 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testDeleteBlogPost() throws Exception {
        BlogPostDTO blogPost = new BlogPostDTO(1L, "Title 1", "Content 1", "Author 1");
        Mockito.when(blogPostService.deleteBlogPost(1L)).thenReturn(blogPost);

        mockMvc.perform(delete("/api/blogposts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Title 1"))
                .andExpect(jsonPath("$.content").value("Content 1"))
                .andExpect(jsonPath("$.author").value("Author 1"));
    }

    @Test
    public void testUpdateBlogPost() throws Exception {
        BlogPostDTO updatedBlogPost = new BlogPostDTO(1L, "Updated Title", "Updated Content", "Updated Author");
        String updatedBlogPostJson = "{\"title\": \"Updated Title\", \"content\": \"Updated Content\", \"author\": \"Updated Author\"}";
        Mockito.when(blogPostService.updateBlogPost(Mockito.eq(1L), Mockito.any())).thenReturn(updatedBlogPost);

        mockMvc.perform(put("/api/blogposts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedBlogPostJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.content").value("Updated Content"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }
}