package com.zja.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * http://localhost:18003/v1/hello?name=瑶瑶
 */
@RequestMapping
@RestController("ServiceHelloController")
public class HelloController {

    @GetMapping("v1/hello")
    public String get(@RequestParam String name) {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "name: " + name;
    }

}
