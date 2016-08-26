package com.cheliadina.service;

import com.cheliadina.domain.Message;
import com.cheliadina.domain.User;
import com.cheliadina.repositories.MessageRepository;
import com.cheliadina.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author nastya
 */
@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Message> getDialog(int currentUserId, int friendId) {
        List<Message> dialog = messageRepository.findByUserFrom_IdAndUserTo_Id(currentUserId, friendId);
        List<Message> receivedMessages = messageRepository.findByUserFrom_IdAndUserTo_Id(friendId, currentUserId);
        for(Message message : receivedMessages)
        {
            List<Message> messagesToSave = new ArrayList<>();
            if (!message.isSeen())
            {
                message.setSeen(true);
                messagesToSave.add(message);
            }
            messageRepository.save(messagesToSave);
        }
        dialog.addAll(receivedMessages);
        Collections.sort(dialog);
        return dialog;
    }

    public void createMessage(String content, int currentUserId, int friendId) {
        User currentUser = userRepository.findOne(currentUserId);
        User friend = userRepository.findOne(friendId);
        messageRepository.save(new Message(content, currentUser, friend));
    }

    public List<Message> getDialogs(int currentUserId) {
        // TODO optimize
        List<Message> allMessages = messageRepository.findByUserFrom_IdOrUserTo_IdOrderByTimeSentAsc(currentUserId, currentUserId);
        Map<Integer, Message> lastMessagesByUserMap = new HashMap<>();
        for (Message message : allMessages) {

            int friendId = (message.getUserFrom().getId() == currentUserId)
                    ? message.getUserTo().getId()
                    : message.getUserFrom().getId();
            lastMessagesByUserMap.put(friendId, message);
        }
        List<Message> dialogs = new ArrayList<>(lastMessagesByUserMap.values());
        Collections.sort(dialogs);
        Collections.reverse(dialogs);
        return dialogs;
    }

}
