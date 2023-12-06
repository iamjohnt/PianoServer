package com.piano.server.stomp;

import com.piano.server.game.session.GameSessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;

@org.springframework.stereotype.Controller

@Component
public class Controller {

    @Autowired
    private GameSessionContainer gameSessions;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestResponse greeting(@Header("simpSessionId") String sessionId, TestSubmission message) throws Exception {
        System.out.println(sessionId);
        return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chord")
    @SendTo("/topic/chord")
    public ChordResponse chordResponse(ChordSubmission message) throws Exception {
        Timestamp ts = Timestamp.from(Instant.now());
        System.out.println(ts.toString() + " LOG: received chord --- " + message.getChord().toString());
        return new ChordResponse("dummy response");
    }

    @MessageMapping("/settings")
    @SendTo("/topic/chord")
    public GameSettingsResponse chordResponse(@Header("simpSessionId") String sessionId, GameSettingsSubmission message) throws Exception {
//        Config config = new Config();
//        GameSession gameSession = new GameSession(config);
//        this.gameSessions.addSession(sessionId, gameSession);
        Timestamp ts = Timestamp.from(Instant.now());
        System.out.println("\n" + ts.toString() + " LOG: received settings --- \n" + message.toString() + "\n");
        return new GameSettingsResponse(true);
    }
}