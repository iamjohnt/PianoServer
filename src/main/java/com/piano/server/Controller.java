package com.piano.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

@org.springframework.stereotype.Controller
public class Controller {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(Hello message) throws Exception {
        return new Response("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}