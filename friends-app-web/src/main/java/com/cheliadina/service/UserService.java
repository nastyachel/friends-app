package com.cheliadina.service;

import com.cheliadina.domain.User;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author nastya
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUser(int id){
        return repository.findOne(id);
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return repository.findByUsernameAndPassword(username, password);
    }

    public void addFriendShip(int userId, int friendId) {
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }

        user.addFriend(friend);
        repository.save(user);
    }

    public void removeFriendship(int userId, int friendId){
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }
        user.removeFriend(friend);
        repository.save(user);
    }

    public boolean checkFriendship(int userId, int friendId){
        if(userId == friendId){
            return false;
        }
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }
        return user.getFriends().contains(friend);
    }

}
