package com.cheliadina.service;

import com.cheliadina.domain.Hobby;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.HobbyRepository;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nastya
 */
@Service
@Transactional
public class HobbyService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HobbyRepository hobbyRepository;

    public void addNewHobby(int userId, String hobbyTitle, String description) {
        User user = userRepository.findOne(userId);
        Hobby hobby = new Hobby();
        hobby.setTitle(hobbyTitle);
        hobby.setDescription(description);
        hobbyRepository.save(hobby);
    }
}
