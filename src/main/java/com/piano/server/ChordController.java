package com.piano.server;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChordController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(ChordSubmission message) throws Exception {
        return new Response("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}