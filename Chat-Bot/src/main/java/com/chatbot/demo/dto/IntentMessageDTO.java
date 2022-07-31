package com.chatbot.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntentMessageDTO {

    private Integer intentMessageId;
    private IntentDTO intentDTO;
    private MessageDTO messageDTO;
    private Double confidence;
}
