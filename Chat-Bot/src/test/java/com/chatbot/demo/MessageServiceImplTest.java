package com.chatbot.demo;

import com.chatbot.demo.dto.MessageDTO;
import com.chatbot.demo.model.Intent;
import com.chatbot.demo.model.IntentMessage;
import com.chatbot.demo.model.Message;
import com.chatbot.demo.model.Reply;
import com.chatbot.demo.repository.MessageRepository;
import com.chatbot.demo.service.IMessageService;
import com.chatbot.demo.service.MessageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MessageServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public IMessageService employeeService() {
            return new MessageServiceImpl();
        }
    }

    @Autowired
    IMessageService messageService;

    @MockBean
    MessageRepository messageRepository;

    @Before
    public void setUp() {
        List<Reply> testReply = List.of(new Reply(1000, "Hello :) How can I help you?", null));
        Message testMessage = new Message(1001, "Hello");
        Intent testIntent = new Intent(10001,"Greeting",null,testReply);
        List<IntentMessage> testIntentMessage = List.of(new IntentMessage(100001,testIntent,testMessage,0.12));
        testIntent.setIntentMessages(testIntentMessage);
        testMessage.setMessageIntents(testIntentMessage);
        Mockito.when(messageRepository.findMessageByMessageText(testMessage.getMessageText()))
                .thenReturn(testMessage);
    }

    @Test
    public void whenValidText_thenMessageShouldBeFound() {
        String text = "Hello";
        MessageDTO found = messageService.getMessageByText(text);
        assertThat(found.getMessageText())
                .isEqualTo(text);
    }

    @Test(expected = NullPointerException.class)
    public void whenNullText_thenMessageThrowsException() {
        String text = null;
        MessageDTO found = messageService.getMessageByText(text);
        assertThat(found.getMessageText())
                .isEqualTo(text);
    }
}
