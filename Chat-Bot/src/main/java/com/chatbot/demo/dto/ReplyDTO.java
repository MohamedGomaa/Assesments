package com.chatbot.demo.dto;

import com.chatbot.demo.model.Intent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Integer replyId;
    private String replyText;
    private IntentDTO replyIntent;
}
