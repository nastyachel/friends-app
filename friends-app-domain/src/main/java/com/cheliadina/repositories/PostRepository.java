package com.cheliadina.repositories;

import com.cheliadina.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author nastya
 */
public interface PostRepository extends JpaRepository<Post, Integer>{
}
