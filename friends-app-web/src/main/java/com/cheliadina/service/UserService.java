package com.cheliadina.service;

import com.cheliadina.domain.User;
import com.cheliadina.model.RegistrationData;
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

    public void removeFriendship(int currentUserId, int friendId) {
        User user = getUser(currentUserId);
        if (user == null) {
            throw new RuntimeException(String.format("No such users in the system (IDs: %d and %d)!", currentUserId, friendId));
        }
        user.removeFriend(friendId);
        repository.save(user);
    }

    public User getFullUser(int userId) {
        User user = getUser(userId);
        Hibernate.initialize(user.getFriends());
        Hibernate.initialize(user.getPosts());
        Hibernate.initialize(user.getHobbies());
        Hibernate.initialize(user.getPlaces());
        return user;
    }

    public List<User> findFriends(int currentUserId){
        User currentUser = getUser(currentUserId);
        List<User> notFriends = repository.findAll();
        notFriends.removeAll(currentUser.getFriends());
        notFriends.remove(currentUser);
        return notFriends;
    }

    public User createNewUser(RegistrationData registrationData){
        User user = new User();
        user.setUsername(registrationData.getUsername());
        user.setPassword(registrationData.getPassword());
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setBirthday(registrationData.getBirthday());
        return repository.save(user);
    }

}
