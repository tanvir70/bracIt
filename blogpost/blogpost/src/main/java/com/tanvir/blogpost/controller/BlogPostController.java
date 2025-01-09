package com.tanvir.blogpost.controller;

import com.tanvir.blogpost.dto.BlogPostDTO;
import com.tanvir.blogpost.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/blogposts", produces = MediaType.APPLICATION_JSON_VALUE)
public class BlogPostController {

    @Autowired
    private BlogPostService blogpostService;

    //Build REST API to create a blog post
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public BlogPostDTO createBlogPost(@Valid @RequestBody BlogPostDTO blogpostDTO) {
        return blogpostService.createBlogPost(blogpostDTO);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<BlogPostDTO> getAllBlogPosts() {
        return blogpostService.getAllBlogPosts();
    }

    @GetMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDTO getBlogPostById(@PathVariable Long id) {
        return blogpostService.getBlogPostById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDTO deleteBlogPost(@PathVariable Long id) {
        return blogpostService.deleteBlogPost(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogPostDTO updateBlogPost(@PathVariable Long id,@Valid @RequestBody BlogPostDTO blogPostDTO) {
        return blogpostService.updateBlogPost(id, blogPostDTO);
    }
}
