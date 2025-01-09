package com.tanvir.blogpost.repositories;

import com.tanvir.blogpost.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
