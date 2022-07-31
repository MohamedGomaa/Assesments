package com.chatbot.demo.dto;

import com.chatbot.demo.model.IntentMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Integer messageId;
    private String messageText;
    private List<IntentMessageDTO> messageIntents;
}
