package com.cheliadina.service;

import com.cheliadina.domain.Place;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.PlaceRepository;
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
public class PlaceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaceRepository placeRepository;

    public Place getPlace(int id){
        return placeRepository.findOne(id);
    }

    public void createPlace(String placeTitle, int currentUserId) {
        User user = userRepository.findOne(currentUserId);
        Place place = new Place(placeTitle.trim());
        user.addPlace(place);
        userRepository.save(user);
    }

    public Collection<User> findFriendsByPlace(String placeTitle, User currentUser){
        Set<User> users = new HashSet<>(userRepository.findByPlaces_TitleIgnoreCase(placeTitle));
        users.remove(currentUser);
        return users;
    }

    public void deletePlace(int placeId, int currentUserId){
        User user = userRepository.findOne(currentUserId);
        user.removePlace(placeId);
        userRepository.save(user);
    }

}
