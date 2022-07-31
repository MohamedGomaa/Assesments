package com.chatbot.demo.utils;

import com.chatbot.demo.dto.IntentDTO;
import com.chatbot.demo.dto.IntentMessageDTO;
import com.chatbot.demo.dto.MessageDTO;
import com.chatbot.demo.dto.ReplyDTO;
import com.chatbot.demo.model.Intent;
import com.chatbot.demo.model.IntentMessage;
import com.chatbot.demo.model.Message;
import com.chatbot.demo.model.Reply;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public MessageDTO mapEntityToDTO(Message messageEntity){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessageId(messageEntity.getMessageId());
        messageDTO.setMessageText(messageEntity.getMessageText());
        List<IntentMessageDTO> intentMessageDTOList = new ArrayList<>();
        for (IntentMessage intentMessage: messageEntity.getMessageIntents()){
            IntentMessageDTO intentMessageDTO = new IntentMessageDTO();
            IntentDTO intentDTO = new IntentDTO();
            intentDTO.setIntentName(intentMessage.getIntent().getIntentName());
            intentDTO.setIntentId(intentMessage.getIntent().getIntentId());
            intentDTO.setIntentReply(new ReplyDTO(intentMessage.getIntent().getIntentReplies().getReplyId(), intentMessage.getIntent().getIntentReplies().getReplyText(), intentDTO));
            intentMessageDTO.setIntentDTO(intentDTO);
            intentMessageDTO.setConfidence(intentMessage.getConfidence());
            intentMessageDTOList.add(intentMessageDTO);
        }
        messageDTO.setMessageIntents(intentMessageDTOList);
        return messageDTO;
    }
}
