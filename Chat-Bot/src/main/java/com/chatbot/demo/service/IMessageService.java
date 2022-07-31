package com.chatbot.demo.service;

import com.chatbot.demo.dto.MessageDTO;

public interface IMessageService {
    MessageDTO getMessageByText(String text);
}
