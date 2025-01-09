package com.tanvir.blogpost.service;

import com.tanvir.blogpost.dto.BlogPostDTO;

import java.util.List;

public interface BlogPostService {
    BlogPostDTO createBlogPost(BlogPostDTO blogPostDTO);

    List<BlogPostDTO> getAllBlogPosts();

    BlogPostDTO getBlogPostById(Long id);

    BlogPostDTO deleteBlogPost(Long id);

    BlogPostDTO updateBlogPost(Long id, BlogPostDTO blogPostDTO);
}
