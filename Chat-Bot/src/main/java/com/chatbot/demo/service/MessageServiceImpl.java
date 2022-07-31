package com.chatbot.demo.service;

import com.chatbot.demo.dto.MessageDTO;
import com.chatbot.demo.repository.MessageRepository;
import com.chatbot.demo.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService{
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;
    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }


    @Override
    public MessageDTO getMessageByText(String text){
        try{
            return new Mapper().mapEntityToDTO(messageRepository.findMessageByMessageText(text));
        }catch(Exception e){
            LOGGER.error("[ MessageServiceImpl ] [ getMessageByText ], throws :> "+ e.getMessage());
            throw e;
        }
    }
}
