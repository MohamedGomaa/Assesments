package com.chatbot.demo.Service;


import com.chatbot.demo.DemoApplication;
import com.chatbot.demo.dto.IntentDTO;
import com.chatbot.demo.dto.IntentMessageDTO;
import com.chatbot.demo.dto.MessageDTO;
import com.chatbot.demo.dto.ReplyDTO;
import com.chatbot.demo.model.Intent;
import com.chatbot.demo.model.IntentMessage;
import com.chatbot.demo.model.Message;
import com.chatbot.demo.model.Reply;
import com.chatbot.demo.service.IChatBotService;
import com.chatbot.demo.service.IMessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class ChatBotServiceImplTest {

    @MockBean
    private IMessageService messageService;

    @Autowired
    private IChatBotService chatBotService;

    @Before
    public void setUp() {
//        MessageDTO testMessage = new MessageDTO(1001, "Hello" ,null);
//        IntentDTO testIntent = new IntentDTO(10001,"Greeting",null,
//                new ReplyDTO(1000, "Hello :) How can I help you?", null));
//        List<IntentMessageDTO> testIntentMessage = List.of(new IntentMessageDTO(100001,testIntent,testMessage,0.12));
//        testIntent.setIntentMessages(testIntentMessage);
//        testMessage.setMessageIntents(testIntentMessage);
//        Mockito.when(messageService.getMessageByText(testMessage.getMessageText()))
//                .thenReturn(testMessage);

        MessageDTO testMessage = new MessageDTO(1002, "Brilliant! Thanks!" ,null);
        IntentDTO testIntent = new IntentDTO(10002,"Thank you",null,
                new ReplyDTO(1011, "It was a pleasure to be of help :)", null));
        IntentDTO testIntent1 = new IntentDTO(10002,"I want to speak with a human",null,
                new ReplyDTO(1012, "Let me transfer you to the first available agent.", null));
        IntentDTO testIntent2 = new IntentDTO(10002,"Login Problems",null,
                new ReplyDTO(1013, "Oh no! Please give me your email and I will fix it.", null));
        IntentDTO testIntent3 = new IntentDTO(10002,"Negative",null,
                new ReplyDTO(1014, "Alright, please let me know if I can help you with anything else!", null));

        List<IntentMessageDTO> testIntentMessage = List.of(new IntentMessageDTO(100002,testIntent,testMessage,0.65),
                new IntentMessageDTO(100003,testIntent1,testMessage,0.12),
                new IntentMessageDTO(100004,testIntent2,testMessage,0.39),
                new IntentMessageDTO(100005,testIntent3,testMessage,0.41));
        testIntent.setIntentMessages(testIntentMessage);
        testMessage.setMessageIntents(testIntentMessage);
        Mockito.when(messageService.getMessageByText(testMessage.getMessageText()))
                .thenReturn(testMessage);
    }
    @Test
    public void whenValidMessage_thenReplyShouldBeReturn() {
        String text = "Hello";
        String reply = chatBotService.getIntentReply(text);
        assertThat(reply)
                .isEqualTo("AI could not give the correct answer");
    }

    @Test
    public void whenMultipleMessageIntents_thenAppropriateReplyShouldBeReturn() {
        String text = "Brilliant! Thanks!";
        String reply = chatBotService.getIntentReply(text);
        assertThat(reply)
                .isEqualTo("It was a pleasure to be of help :)");
    }





}
