package com.chatbot.demo.dto;

import com.chatbot.demo.model.IntentMessage;
import com.chatbot.demo.model.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntentDTO {
    private Integer intentId;
    private String intentName;
    private List<IntentMessageDTO> intentMessages;
    private ReplyDTO intentReply;

}
