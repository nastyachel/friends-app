package com.cheliadina.service;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.MessageRepository;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author nastya
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    public List<Message> getDialog(int currentUserId, int friendId){
        List<Message> dialog = messageRepository.findByUserFrom_IdAndUserTo_Id(currentUserId, friendId);
        dialog.addAll(messageRepository.findByUserFrom_IdAndUserTo_Id(friendId, currentUserId));
        Collections.sort(dialog);
        return dialog;
    }

    public void createMessage(String content, int currentUserId, int friendId){
        User currentUser = userRepository.findOne(currentUserId);
        User friend = userRepository.findOne(friendId);
        messageRepository.save(new Message(content, currentUser, friend));
    }
}
