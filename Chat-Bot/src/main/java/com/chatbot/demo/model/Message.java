package com.chatbot.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer messageId;

    @Column(nullable = false)
    private String messageText;

    @OneToMany(mappedBy = "message")
    private List<IntentMessage> messageIntents;

    public Message (Integer id, String messageText){
        this.messageId = id;
        this.messageText = messageText;
    }
}
