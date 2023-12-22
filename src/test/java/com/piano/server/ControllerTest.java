package com.piano.server;

import com.piano.server.game.session.GameSessionContainer;
import com.piano.server.game.util.*;
import com.piano.server.stomp.Controller;
import com.piano.server.stomp.response.*;
import com.piano.server.stomp.submission.CreateSessionSubmission;
import com.piano.server.stomp.submission.GameSettingsSubmission;
import com.piano.server.stomp.submission.StartGameSubmission;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller controller;
    private String sessionIdTest;
    private GameSettingsSubmission defaultSettings;

    @BeforeEach
    void setUp() {
        this.controller = new Controller();
        this.controller.setGameSessions(new GameSessionContainer());
        this.controller.setGameState(new GameState(GameState.State.UNSTARTED));
        this.sessionIdTest = "qwerasdf1234";
        this.defaultSettings = new GameSettingsSubmission()
            .setChordPool(ChordPool.NOTE)
            .setKeySigNote(KeySigNote.C)
            .setKeySigMode(KeySigMode.MAJOR)
            .setWhichHands(WhichHands.RIGHT)
            .setRightMin(60)
            .setRightMax(60)
            .setLength(3);
    }

    @AfterEach
    void tearDown() {
        this.controller = null;
        this.defaultSettings = null;
    }

    @Test
    void test_create_session() {
        CreateSessionSubmission submission = new CreateSessionSubmission();
        CreateSessionResponse response = controller.handleCreateSession(sessionIdTest, submission);
        assertTrue(response.getIsCreateSessionSuccess());
    }

    @Test
    void test_delete_session() {
        CreateSessionSubmission submission = new CreateSessionSubmission();
        controller.handleCreateSession(sessionIdTest, submission);

        EndSessionResponse response = controller.handleEndSession(sessionIdTest, new EndSessionSubmission());
        System.out.println(response.getMessage());
        assertTrue(response.isEndSessionSuccess());
    }

    @Test
    void test_game_session_recieve_settings() {
        CreateSessionSubmission submission = new CreateSessionSubmission();
        controller.handleCreateSession(sessionIdTest, submission);

        GameSettingsResponse response = controller.handleGameSettings(sessionIdTest, defaultSettings);
        assertTrue(response.isSuccess());
    }



}