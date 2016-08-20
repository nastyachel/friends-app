package com.cheliadina.repositories;

import com.cheliadina.domain.Hobby;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author nastya
 */
public interface HobbyRepository extends CrudRepository<Hobby, Integer>{

    List<Hobby> findByTitle(String title);

}
