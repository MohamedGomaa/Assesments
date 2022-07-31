package com.chatbot.demo.service;

import com.chatbot.demo.dto.IntentDTO;
import com.chatbot.demo.dto.IntentMessageDTO;
import com.chatbot.demo.dto.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotServiceImpl implements IChatBotService{
    private final static Logger LOGGER = LoggerFactory.getLogger(ChatBotServiceImpl.class);
    private final Double CONFIDENCE_THRESHOLD = 0.4;

    private final IMessageService messageService;
    public ChatBotServiceImpl(IMessageService messageService){
        this.messageService = messageService;
    }


    @Override
    public String getIntentReply(String messageText){
        try{
            MessageDTO messageDTO =  messageService.getMessageByText(messageText);
            if(messageDTO.getMessageIntents() != null && !messageDTO.getMessageIntents().isEmpty()) {
                List<IntentMessageDTO> confidentMessageIntents = getConfidentIntents(messageDTO.getMessageIntents());
                return !confidentMessageIntents.isEmpty() ?
                        getMostAppropriateIntent(confidentMessageIntents).getIntentReply().getReplyText()
                        : "AI could not give the correct answer";
            }else{
                return "AI could not give the correct answer";
            }
        }catch(Exception e){
            LOGGER.error("[ ChatBotServiceImpl ] [ getIntentReply ], throws :> "+ e.getMessage());
            throw e;
        }
    }

    private List<IntentMessageDTO> getConfidentIntents(List<IntentMessageDTO> intentMessageDTOList){
        try{
            List<IntentMessageDTO> confidentIntentMessagesDTOs = new ArrayList<>();
            for (IntentMessageDTO intentMessageDTO : intentMessageDTOList){
                if(intentMessageDTO.getConfidence() >= CONFIDENCE_THRESHOLD){
                    confidentIntentMessagesDTOs.add(intentMessageDTO);
                }
            }
            return confidentIntentMessagesDTOs;

        }catch(Exception e){
            LOGGER.error("[ ChatBotServiceImpl ] [ getConfidentIntents ], throws :> "+ e.getMessage());
            throw e;
        }
    }

    private IntentDTO getMostAppropriateIntent(List<IntentMessageDTO> intentMessageDTOList){
        try{
            IntentDTO mostAppropriateIntent = null;
            Double largestConfidenceValue = 0.0;
            for (IntentMessageDTO intentMessageDTO : intentMessageDTOList){
                if(intentMessageDTO.getConfidence() >= largestConfidenceValue){
                    largestConfidenceValue = intentMessageDTO.getConfidence();
                    mostAppropriateIntent = intentMessageDTO.getIntentDTO();
                }
            }
            return mostAppropriateIntent;
        }catch(Exception e){
            LOGGER.error("[ ChatBotServiceImpl ] [ getMostAppropriateIntent ], throws :> "+ e.getMessage());
            throw e;
        }
    }

}
