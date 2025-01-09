package com.tanvir.blogpost.mapper;

import com.tanvir.blogpost.dto.BlogPostDTO;
import com.tanvir.blogpost.model.BlogPost;


public class BlogPostMapper {
    public static BlogPostDTO toBlogPostDTO(BlogPost blogPost) {
        BlogPostDTO blogPostDTO = new BlogPostDTO();
        blogPostDTO.setId(blogPost.getId());
        blogPostDTO.setTitle(blogPost.getTitle());
        blogPostDTO.setContent(blogPost.getContent());
        blogPostDTO.setAuthor(blogPost.getAuthor());

        return blogPostDTO;
    }

    public static BlogPost toBlogPost(BlogPostDTO blogPostDTO) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostDTO.getId());
        blogPost.setTitle(blogPostDTO.getTitle());
        blogPost.setContent(blogPostDTO.getContent());
        blogPost.setAuthor(blogPostDTO.getAuthor());

        return blogPost;
    }
}
