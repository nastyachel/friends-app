package com.cheliadina.repositories;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;

/**
 * @author nastya
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByUserFrom_IdAndUserTo_Id(int userFrom, int userToId);

}