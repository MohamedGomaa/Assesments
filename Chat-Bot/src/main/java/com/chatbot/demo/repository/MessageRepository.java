package com.chatbot.demo.repository;

import com.chatbot.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    Message findMessageByMessageText(String text);
}
