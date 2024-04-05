package com.piano.server.stomp;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.session.GameSession;
import com.piano.server.game.util.GameState;
import com.piano.server.stomp.response.*;
import com.piano.server.stomp.submission.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

@org.springframework.stereotype.Controller

@Component
@RestController
public class Controller {

    private Map<String, GameSession> gameSessions;

    private GameState gameState;

    // for health checks
    @GetMapping("/")
    public String helloRest() {
        return "Health check success!";
    }


    @MessageMapping("/hello")
    @SendToUser("/queue/hello")
    public TestResponse handleHello(@Header("simpSessionId") String sessionId, TestSubmission message) {
        System.out.println(sessionId);
        return new TestResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/chord")
    @SendToUser("/queue/chord")
    public ChordResponse handleChord(@Header("simpSessionId") String sessionId, ChordSubmission message) {
        Timestamp ts = Timestamp.from(Instant.now());
        GameSession session = this.gameSessions.get(sessionId); //TODO possible null, so can cause null pointer later
        Chord chord = new Chord(message.getChord());
        ChordResponse response = session.checkChordAdvanceIfCorrect(chord);
        return response;
    }

    @MessageMapping("/settings")
    @SendToUser("/queue/settings")
    public GameSettingsResponse handleGameSettings(@Header("simpSessionId") String sessionId, GameSettingsSubmission settings) {

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
        gameSessions.put(sessionId, session);
        return new GameSettingsResponse(true, "game settings applied");
    }

    @MessageMapping("/startgame")
    @SendToUser("/queue/startgame")
    public StartGameResponse handleStartGame(@Header("simpSessionId") String sessionId, StartGameSubmission start) {

        StartGameResponse response = null;
        GameState.State state = gameState.getCurrentState();
        GameSession session = gameSessions.get(sessionId);

        if (session == null) {
            return new StartGameResponse(false, "game session does not exist", null);
        }

        switch (state) {
            case STARTING -> response = new StartGameResponse(false, "game is starting", null);
            case STARTED -> response = new StartGameResponse(false, "game is already started", null);
            case FINISHING -> response = new StartGameResponse(false, "game is finishing", null);
            case FINISHED -> response = new StartGameResponse(false, "game is already finished", null);
            case UNSTARTED -> {
                session.startGame();
                response = new StartGameResponse(true, "game has started", session.getMusic());
            }
        }
        return response;
    }


    @MessageMapping("/endgame")
    @SendToUser("/queue/endgame")
    public EndGameResponse handleEndGame(@Header("simpSessionId") String sessionId, EndGameSubmission end) {
        EndGameResponse response = null;
        GameState.State state = gameState.getCurrentState();
        GameSession session = gameSessions.get(sessionId);

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

    @MessageMapping("/startsession")
    @SendToUser("/queue/startsession")
    public CreateSessionResponse handleCreateSession(@Header("simpSessionId") String sessionId, CreateSessionSubmission startSessionSubmission) {
        if (gameSessions.containsKey(sessionId)) {
            return new CreateSessionResponse(false, "session is already created");
        } else {
            gameSessions.put(sessionId, new GameSession(sessionId));
            return new CreateSessionResponse(true, "session has been created");
        }
    }

    @MessageMapping("/endsession")
    @SendToUser("/queue/endsession")
    public EndSessionResponse handleEndSession(@Header("simpSessionId") String sessionId, EndSessionSubmission endSession) {
        if (gameSessions.containsKey(sessionId)) {
            gameSessions.remove(sessionId);
            return new EndSessionResponse(true, "session has been ended");
        } else {
            return new EndSessionResponse(false, "session does not exist, could not end");
        }
    }

    @Autowired
    public void setGameSessions(Map<String, GameSession> gameSessions) {
        this.gameSessions = gameSessions;
    }

    @Autowired
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}