package com.chatbot.demo.repository;

import com.chatbot.demo.model.IntentMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentMessageRepository extends JpaRepository<IntentMessage, Integer> {
}
