package com.tanvir.blogpost.service;

import com.tanvir.blogpost.dto.BlogPostDTO;
import com.tanvir.blogpost.exceptions.ResourceNotFoundException;
import com.tanvir.blogpost.mapper.BlogPostMapper;
import com.tanvir.blogpost.model.BlogPost;
import com.tanvir.blogpost.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Override
    public BlogPostDTO createBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = BlogPostMapper.toBlogPost(blogPostDTO); // Convert DTO to Entity
        BlogPost savedBlogPost = blogPostRepository.save(blogPost);

        return BlogPostMapper.toBlogPostDTO(savedBlogPost);
    }

    @Override
    public List<BlogPostDTO> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepository.findAll();

        return blogPosts.stream().map(BlogPostMapper::toBlogPostDTO).toList();
    }

    @Override
    public BlogPostDTO getBlogPostById(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BlogPost", "id", id));

        return BlogPostMapper.toBlogPostDTO(blogPost);
    }

    @Override
    public BlogPostDTO deleteBlogPost(Long id) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BlogPost", "id", id));
        blogPostRepository.delete(blogPost);

        return BlogPostMapper.toBlogPostDTO(blogPost);
    }

    @Override
    public BlogPostDTO updateBlogPost(Long id, BlogPostDTO blogPostDTO) {
        BlogPost blogPost = blogPostRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("BlogPost", "id", id));
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());
        BlogPost updatedBlogPost = blogPostRepository.save(blogPost);

        return BlogPostMapper.toBlogPostDTO(updatedBlogPost);
    }
}
