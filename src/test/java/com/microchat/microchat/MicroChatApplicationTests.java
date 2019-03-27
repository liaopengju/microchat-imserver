package com.microchat.microchat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicroChatApplicationTests {


    @Test
    public void contextLoads() {
        String test ="1";
        Assert.assertEquals(test,test);
    }

}
