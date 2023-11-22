package com.piano.server.stomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;

@org.springframework.stereotype.Controller
public class Controller {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestResponse greeting(TestSubmission message) throws Exception {
        return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chord")
    @SendTo("/topic/chord")
    public ChordResponse chordResponse(ChordSubmission message) throws Exception {
        Timestamp ts = Timestamp.from(Instant.now());
        System.out.println(ts.toString() + " LOG: received chord --- " + message.getChord().toString());
        return new ChordResponse("dummy response");
    }


}