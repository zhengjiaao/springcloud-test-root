package com.zja.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "attributes")
public class MyAttributes {

    private String config;
    private List<String> list;
    private HashMap<String, String> map;
}
