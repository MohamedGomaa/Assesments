package com.chatbot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer replyId;

    @Column(nullable = false)
    private String replyText;

    @OneToOne
    @JoinColumn(name = "intentId")
    private Intent replyIntent;

}
