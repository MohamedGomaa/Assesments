package com.chatbot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntentMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer intentMessageId;

    @ManyToOne
    @JoinColumn(name = "intent_id")
    private Intent intent;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @Column
    private Double confidence;
}
