package com.chatbot.demo.Service;

import com.chatbot.demo.DemoApplication;
import com.chatbot.demo.dto.MessageDTO;
import com.chatbot.demo.model.Intent;
import com.chatbot.demo.model.IntentMessage;
import com.chatbot.demo.model.Message;
import com.chatbot.demo.model.Reply;
import com.chatbot.demo.repository.MessageRepository;
import com.chatbot.demo.service.IMessageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= DemoApplication.class)
public class MessageServiceImplTest {

    @Autowired
    private IMessageService messageServiceTest;


    @MockBean
    MessageRepository messageRepository;

    @Before
    public void setUp() {
        Message testMessage = new Message(1001, "Hello");
        Intent testIntent = new Intent(10001,"Greeting",null,
                new Reply(1000, "Hello :) How can I help you?", null));
        List<IntentMessage> testIntentMessage = List.of(new IntentMessage(100001,testIntent,testMessage,0.12));
        testIntent.setIntentMessages(testIntentMessage);
        testMessage.setMessageIntents(testIntentMessage);
        Mockito.when(messageRepository.findMessageByMessageText(testMessage.getMessageText()))
                .thenReturn(testMessage);
    }

    @Test
    public void whenValidText_thenMessageShouldBeFound() {
        String text = "Hello";
        MessageDTO found = messageServiceTest.getMessageByText(text);
        assertThat(found.getMessageText())
                .isEqualTo(text);
    }

    @Test(expected = NullPointerException.class)
    public void whenNullText_thenMessageThrowsException() {
        String text = null;
        MessageDTO found = messageServiceTest.getMessageByText(text);
        assertThat(found.getMessageText())
                .isEqualTo(text);
    }
}
