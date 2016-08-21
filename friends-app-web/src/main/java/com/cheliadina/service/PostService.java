package com.cheliadina.service;

import com.cheliadina.domain.Post;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nastya
 */
@Service
@Transactional
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public void createPost(String content, int currentUserId) {
        Post post = new Post();
        post.setContent(content);
        User currentUser = userService.getFullUser(currentUserId);
        currentUser.addPost(post);
        userRepository.save(currentUser);
    }
}
