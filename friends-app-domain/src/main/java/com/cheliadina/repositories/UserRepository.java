package com.cheliadina.repositories;

import com.cheliadina.domain.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @author nastya
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
