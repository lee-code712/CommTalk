package com.commtalk.service;

import com.commtalk.CommtalkApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
