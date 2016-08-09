package com.cheliadina.config;

import com.cheliadina.domain.User;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setFirstName("Freddy");
            user1.setUsername("admin");
            user1.setPassword("123");
            User user2 = new User();
            user2.setFirstName("Lucky");
            user2.setUsername("lucky12");
            user2.setPassword("123");
            User user3 = new User();
            user3.setFirstName("Max");
            user3.setUsername("maxmax");
            user3.setPassword("123");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }
}

