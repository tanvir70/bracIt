package com.tanvir.blogpost.service;

import com.tanvir.blogpost.dto.BlogPostDTO;
import com.tanvir.blogpost.exceptions.ResourceNotFoundException;
import com.tanvir.blogpost.mapper.BlogPostMapper;
import com.tanvir.blogpost.model.BlogPost;
import com.tanvir.blogpost.repositories.BlogPostRepository;
import jakarta.persistence.Id;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogPostServiceImplTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private BlogPostServiceImpl blogPostService;

    @Test
    public void testCreateBlogPost() {
        // Arrange
        BlogPost blogPost = new BlogPost(1L, "Test Title", "Test Content", "Test Author");

        BlogPostDTO blogPostDTO = BlogPostMapper.toBlogPostDTO(blogPost);
        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(blogPost);

        // Act
        BlogPostDTO result = blogPostService.createBlogPost(blogPostDTO);

        // Assert
        assertNotNull(result);
        assertEquals(blogPost.getId(), result.getId());
        assertEquals(blogPost.getTitle(), result.getTitle());
        assertEquals(blogPost.getContent(), result.getContent());
        assertEquals(blogPost.getAuthor(), result.getAuthor());
    }

    @Test
    public void testGetAllBlogPosts() {
        BlogPost blogPost1 = new BlogPost();
        blogPost1.setTitle("Title 1");

        BlogPost blogPost2 = new BlogPost();
        blogPost2.setTitle("Title 2");

        when(blogPostRepository.findAll()).thenReturn(Arrays.asList(blogPost1, blogPost2));

        List<BlogPostDTO> result = blogPostService.getAllBlogPosts();

        assertEquals(2, result.size());
        assertEquals("Title 1", result.get(0).getTitle());
        assertEquals("Title 2", result.get(1).getTitle());
    }

    @Test
    public void testGetBlogPostById() {
        BlogPost blogPost = new BlogPost(1L, "Title 1", "Content 1", "Author 1");

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));

        BlogPostDTO result = blogPostService.getBlogPostById(1L);

        assertEquals(blogPost.getId(), result.getId());
        assertEquals(blogPost.getTitle(), result.getTitle());
        assertEquals(blogPost.getContent(), result.getContent());
        assertEquals(blogPost.getAuthor(), result.getAuthor());
    }


    @Test
    public void testDeleteBlogPost() {
        BlogPost blogPost = new BlogPost(1L, "Title 1", "Content 1", "Author 1");

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));

        BlogPostDTO result = blogPostService.deleteBlogPost(1L);

        assertNotNull(result);
        assertEquals(blogPost.getId(), result.getId());
        assertEquals(blogPost.getTitle(), result.getTitle());

    }

    @Test
    public void testUpdateBlogPost() {
        BlogPost blogPost = new BlogPost(1L, "Old Title", "Old Content", "Old Author");

        BlogPostDTO updatedDTO = new BlogPostDTO(1L, "New Title", "New Content", "New Author");

        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));
        when(blogPostRepository.save(any(BlogPost.class))).thenReturn(blogPost);

        BlogPostDTO result = blogPostService.updateBlogPost(1L, updatedDTO);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        assertEquals("New Content", result.getContent());
    }

}
