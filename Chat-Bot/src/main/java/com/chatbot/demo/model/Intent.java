package com.chatbot.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer intentId;

    @Column
    private String intentName;

    @OneToMany(mappedBy = "intent")
    private List<IntentMessage> intentMessages;

    @OneToOne(mappedBy = "replyIntent")
    private Reply intentReplies;

    public Intent(Integer id){
        this.intentId = id;
    }

}
