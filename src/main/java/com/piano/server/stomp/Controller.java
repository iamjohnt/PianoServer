package com.piano.server.stomp;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.session.GameSession;
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
    public ChordResponse chordResponse(@Header("simpSessionId") String sessionId, ChordSubmission message) throws Exception {
        Timestamp ts = Timestamp.from(Instant.now());
        GameSession session = this.gameSessions.getSession(sessionId);
        Chord chord = new Chord(message.getChord());
        session.validateChord(chord);
        return new ChordResponse("dummy response");
    }

    @MessageMapping("/settings")
    @SendTo("/topic/chord")
    public GameSettingsResponse chordResponse(@Header("simpSessionId") String sessionId, GameSettingsSubmission settings) throws Exception {

        Timestamp ts = Timestamp.from(Instant.now());

        Config config = new Config()
                .setChordPool(settings.getChordPool())
                .setKeySigNote(settings.getKeySigNote())
                .setKeySigMode(settings.getKeySigMode())
                .setHands(settings.getWhichHands())
                .setLength(settings.getLength())
                .setLeftMin(settings.getLeftMin())
                .setLeftMax(settings.getLeftMax())
                .setRightMin(settings.getRightMin())
                .setRightMax(settings.getRightMax());

        GameSession session = new GameSession(sessionId, config);
        session.startGame();
        gameSessions.addSession(sessionId, session);
        return new GameSettingsResponse(true);
    }
}