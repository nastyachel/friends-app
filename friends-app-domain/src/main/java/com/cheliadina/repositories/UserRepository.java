package com.cheliadina.repositories;

import com.cheliadina.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


/**
 * @author nastya
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByLastName(String lastName);

    User findByUsernameAndPassword(String username, String password);

}
