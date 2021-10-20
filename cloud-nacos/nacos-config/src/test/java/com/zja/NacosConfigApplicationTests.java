package com.zja;

import com.zja.entity.MyAttributes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NacosConfigApplicationTests {

    @Autowired
    private MyAttributes myAttributes;

    @Test
    void contextLoads() {
        System.out.println(myAttributes);
    }

}
