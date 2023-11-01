package com.example.messagingstompwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingStompWebsocketApplication {

    public static void main(String[] args) {
        System.out.println("proof i'm being changed");
        SpringApplication.run(MessagingStompWebsocketApplication.class, args);
    }
}