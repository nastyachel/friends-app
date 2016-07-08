package com.cheliadina.repositories;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;

/**
 * @author nastya
 */
public interface MessageRepository extends CrudRepository<Message, Integer> {

    Iterable<Message> findByPersonFrom(User personFrom);

    Iterable<Message> findByPersonFromAndPersonTo(User personFrom, User personTo);

}
