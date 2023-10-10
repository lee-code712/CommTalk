package com.commtalk.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes= CommtalkApplication.class)
public class myPageServiceTest {

    @Autowired
    MyPageService myPageService;

    @Test
    public void testGetMember() {
        try {
            String jsonStr = myPageService.getMember(10L);
            System.out.println(jsonStr);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateMember() {
        try {
            Map<String, Object> command = new HashMap<>();
            command.put("orgPassword", "1234");
            command.put("nickname", "commtalk");
            command.put("username", "땡땡이");
            command.put("email", "commtalk@example.com");
            command.put("phone", "010-1222-1212");
            String jsonStr = myPageService.updateMember(10L, command);
            System.out.println(jsonStr);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetPostByType() {
        try {
            String jsonStr = myPageService.getPostsByType(11L, "scraped");
            System.out.println(jsonStr);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
