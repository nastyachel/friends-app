package com.cheliadina.service;

import com.cheliadina.domain.Post;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.PostRepository;
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
    PostRepository postRepository;

    @Autowired
    UserService userService;

    public void createPost(String content, int currentUserId) {
        if (content.trim().isEmpty())
        {
            return;
        }
        Post post = new Post(content);
        User currentUser = userService.getFullUser(currentUserId);
        currentUser.addPost(post);
        userRepository.save(currentUser);
    }

    public void deletePost(int postId, int currentUserId) {
        User currentUser = userService.getFullUser(currentUserId);
        currentUser.removePost(postId);
        userRepository.save(currentUser);
    }

    public void likePost(int postId, int userLikedId){
        User userLiked = userRepository.findOne(userLikedId);
        Post post = postRepository.findOne(postId);
        post.likeOrDislikePost(userLiked);
        postRepository.save(post);
    }

}
