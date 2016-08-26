package com.cheliadina.config;

import com.cheliadina.domain.*;
import com.cheliadina.repositories.MessageRepository;
import com.cheliadina.repositories.PostRepository;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author nastya
 */
@Component
public class DataCreationApplicationListener implements ApplicationListener<ContextRefreshedEvent>
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setFirstName("Freddy");
            user1.setLastName("Merkury");
            user1.setUsername("admin");
            user1.setPassword("123");
            user1.setBirthday(1993, 8, 19);
            User user2 = new User();
            user2.setFirstName("Lucky");
            user2.setLastName("Doggerty");
            user2.setUsername("lucky12");
            user2.setPassword("123");
            user2.setBirthday(1993, 5, 23);
            user2.addPlace(new Place("Dnipro"));
            User user3 = new User();
            user3.setFirstName("Max");
            user3.setLastName("Biggls");
            user3.setUsername("maxmax");
            user3.setPassword("123");
            user3.setBirthday(1992, 1, 10);
            Post postWithLikes = new Post("Hello world!");
            user3.addPost(postWithLikes);
            user3.addPost(new Post("What a wonderful day! :)"));
            user3.addHobby(new Hobby("hobby1"));
            user3.addHobby(new Hobby("hobby1"));
            user3.addPlace(new Place("Dnipro"));
            user3.addPlace(new Place("Odessa"));
            user3.addFriend(user2);
            user3.addFriend(user1);

            User user4 = new User();
            user4.setFirstName("Jake");
            user4.setLastName("Petersen");
            user4.setUsername("jakejake");
            user4.setPassword("123");
            user4.setBirthday(1992, 2, 2);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

            postWithLikes.likeOrDislikePost(user1);
            postWithLikes.likeOrDislikePost(user2);
            postWithLikes.likeOrDislikePost(user3);
            postWithLikes.likeOrDislikePost(user4);
            postRepository.save(postWithLikes);

            messageRepository.save(new Message("Hello Max", user1, user3));

            messageRepository.save(new Message("Hi", user1, user2, true));
            messageRepository.save(new Message("Hello. How are you?", user2, user1, true));
            messageRepository.save(new Message("Fine. And you?", user1, user2, true));
            messageRepository.save(new Message("Good, thank you! What are your plans for weekend?", user2, user1));
        }
    }
}

