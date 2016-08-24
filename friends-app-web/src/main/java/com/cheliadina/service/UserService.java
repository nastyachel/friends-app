package com.cheliadina.service;

import com.cheliadina.domain.User;
import com.cheliadina.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nastya
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository repository;

    public User getUser(int id) {
        User user = repository.findOne(id);
        if (user == null) {
            throw new RuntimeException(String.format("No such user in the system (ID: %d)!", id));
        }
        return user;
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password);
    }

    public void addFriendShip(int userId, int friendId) {
        if (userId == friendId) {
            return;
        }
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }

        user.addFriend(friend);
        repository.save(user);
    }

    public void removeFriendship(int userId, int friendId) {
        if (userId == friendId) {
            return;
        }
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }
        user.removeFriend(friend);
        repository.save(user);
    }

    public boolean checkFriendship(int userId, int friendId) {
        if (userId == friendId) {
            return false;
        }
        User user = repository.findOne(userId);
        User friend = repository.findOne(friendId);
        if (user == null || friend == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", userId, friendId));
        }
        return user.getFriends().contains(friend);
    }

    public User getFullUser(int userId) {
        User user = getUser(userId);
        Hibernate.initialize(user.getFriends());
        Hibernate.initialize(user.getPosts());
        Hibernate.initialize(user.getHobbies());
        return user;
    }

    public List<User> findFriends(int currentUserId){
        User currentUser = getUser(currentUserId);
        List<User> notFriends = repository.findAll(); // todo add paging
        notFriends.removeAll(currentUser.getFriends());
        notFriends.remove(currentUser);
        return notFriends;
    }
}
