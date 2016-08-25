package com.cheliadina.service;

import com.cheliadina.domain.Hobby;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.HobbyRepository;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nastya
 */
@Service
@Transactional
public class HobbyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HobbyRepository hobbyRepository;

    public Hobby getHobby(int id){
        return hobbyRepository.findOne(id);
    }

    public void createHobby(String hobbyTitle, int currentUserId) {
        User user = userRepository.findOne(currentUserId);
        Hobby hobby = new Hobby(hobbyTitle.trim());
        user.addHobby(hobby);
        userRepository.save(user);
    }

    public Collection<User> findFriendsByHobby(String hobbyTitle, User currentUser){
        Set<User> users = new HashSet<>(userRepository.findByHobbies_TitleIgnoreCase(hobbyTitle));
        users.remove(currentUser);
        return users;
    }

    public void deleteHobby(int hobbyId, int currentUserId){
        User user = userRepository.findOne(currentUserId);
        user.removeHobby(hobbyId);
        userRepository.save(user);
    }

}
