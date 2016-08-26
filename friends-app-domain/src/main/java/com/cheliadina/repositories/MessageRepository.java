package com.cheliadina.repositories;

import com.cheliadina.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author nastya
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(int userFromId, int userToId);

    List<Message> findByUserFrom_IdAndUserTo_Id(int userFromId, int userToId);

    int countByUserTo_IdAndSeen(int userToId, boolean seen);

}