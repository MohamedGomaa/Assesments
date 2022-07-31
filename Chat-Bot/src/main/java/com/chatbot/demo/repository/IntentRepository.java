package com.chatbot.demo.repository;

import com.chatbot.demo.model.Intent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends JpaRepository<Intent, Integer> {
}
