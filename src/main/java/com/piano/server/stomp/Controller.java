package com.piano.server.stomp;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.session.GameSession;
import com.piano.server.game.session.GameSessionContainer;
import com.piano.server.game.util.GameState;
import com.piano.server.stomp.response.*;
import com.piano.server.stomp.submission.*;
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

    @Autowired
    private GameState gameState;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public TestResponse greeting(@Header("simpSessionId") String sessionId, TestSubmission message) throws Exception {
        System.out.println(sessionId);
        return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chord")
    @SendTo("/topic/chord")
    public ChordResponse handleChord(@Header("simpSessionId") String sessionId, ChordSubmission message) throws Exception {
        Timestamp ts = Timestamp.from(Instant.now());
        GameSession session = this.gameSessions.getSession(sessionId);
        Chord chord = new Chord(message.getChord());
        ChordResponse response = session.checkChordAdvanceIfCorrect(chord);
        return response;
    }

    @MessageMapping("/settings")
    @SendTo("/topic/chord")
    public GameSettingsResponse handleGameSettings(@Header("simpSessionId") String sessionId, GameSettingsSubmission settings) throws Exception {

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
        gameSessions.addSession(sessionId, session);
        return new GameSettingsResponse(true);
    }

    @MessageMapping("/startgame")
    @SendTo("/topic/chord")
    public StartGameResponse handleStartGame(@Header("simpSessionId") String sessionId, StartGameSubmission start) {

        StartGameResponse response = null;
        GameState.State state = gameState.getCurrentState();
        GameSession session = gameSessions.getSession(sessionId);

        if (session == null) {
            return new StartGameResponse(false, "game session does not exist");
        }

        switch (state) {
            case STARTING -> response = new StartGameResponse(false, "game is starting");
            case STARTED -> response = new StartGameResponse(false, "game is already started");
            case FINISHING -> response = new StartGameResponse(false, "game is finishing");
            case FINISHED -> response = new StartGameResponse(false, "game is already finished");
            case UNSTARTED -> {
                session.startGame();
                response = new StartGameResponse(true, "game has started");
            }
        }
        return response;
    }


    @MessageMapping("/endgame")
    @SendTo("/topic/chord")
    public EndGameResponse handleEndGame(@Header("simpSessionId") String sessionId, EndGameSubmission end) {
        EndGameResponse response = null;
        GameState.State state = gameState.getCurrentState();
        GameSession session = gameSessions.getSession(sessionId);

        if (session == null) {
            response = new EndGameResponse(false, "game session does not exist");
        } else if (state == GameState.State.UNSTARTED) {
            response = new EndGameResponse(false, "game is already unstarted, cannot end game");
        } else {
            session.endGame();
            response = new EndGameResponse(true, "game has ended");
        }
        return response;
    }


}