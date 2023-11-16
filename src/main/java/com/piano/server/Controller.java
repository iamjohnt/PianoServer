package com.piano.server;

import com.piano.server.in.Chord;
import com.piano.server.in.Hello;
import com.piano.server.out.ChordResponse;
import com.piano.server.out.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Response greeting(Hello message) throws Exception {
        return new Response("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chord")
    @SendTo("/topic/chord")
    public ChordResponse chordResponse(Chord message) throws Exception {
        Timestamp ts = Timestamp.from(Instant.now());
        System.out.println(ts.toString() + " LOG: received chord --- " + message.getChord().toString());
        return new ChordResponse("dummy response");
    }


}