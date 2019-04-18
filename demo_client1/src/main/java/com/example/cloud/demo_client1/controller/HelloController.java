package com.example.cloud.demo_client1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${client2.port}")
    private String port;
    
    @Value("${client2.service.id}")
    private String client;
    
    @RequestMapping("/hello")
    public String hello(){
        String body = restTemplate.getForEntity("http://" + client + "/world",String.class).getBody();
        return body;
    }
    
    @RequestMapping("/world")
    public String world(){
        return client + "：" + port;
    }
}
