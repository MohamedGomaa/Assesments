package com.chatbot.demo.controller;

import com.chatbot.demo.payload.ChatBotRequest;
import com.chatbot.demo.service.IChatBotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class ChatBotController {
    private final IChatBotService chatBotService;
    public ChatBotController(IChatBotService chatBotService){
        this.chatBotService = chatBotService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody ChatBotRequest chatBotRequestPayload){
        try{
            return new ResponseEntity<>(chatBotService.getIntentReply(chatBotRequestPayload.getMessageText()), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Error happened : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
