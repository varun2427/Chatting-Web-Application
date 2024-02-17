package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.Message;

@Service
public class MessageDAO {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> showAllMessages() {
        return messageRepository.findAll();
    }

    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message showMessageById(int message_id) {
        return messageRepository.findById(message_id).orElse(null);
    }

    public List<Message> findAllMessages(int sender, int recipient) {
        return messageRepository.findAllMessages(sender, recipient);
    }
}
