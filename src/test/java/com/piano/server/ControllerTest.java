package com.piano.server;

import com.piano.server.game.session.GameSessionContainer;
import com.piano.server.game.util.*;
import com.piano.server.stomp.Controller;
import com.piano.server.stomp.response.*;
import com.piano.server.stomp.submission.ChordSubmission;
import com.piano.server.stomp.submission.CreateSessionSubmission;
import com.piano.server.stomp.submission.GameSettingsSubmission;
import com.piano.server.stomp.submission.StartGameSubmission;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    void test_start_game() {
        CreateSessionSubmission submission = new CreateSessionSubmission();
        controller.handleCreateSession(sessionIdTest, submission);
        controller.handleGameSettings(sessionIdTest, defaultSettings);

        StartGameResponse response = controller.handleStartGame(sessionIdTest, new StartGameSubmission());
        assertTrue(response.getStartGameSuccess());
    }

    @Test
    void test_send_chord_game() {
        controller.handleCreateSession(sessionIdTest, new CreateSessionSubmission());
        controller.handleGameSettings(sessionIdTest, defaultSettings);
        controller.handleStartGame(sessionIdTest, new StartGameSubmission());

        Set<Integer> chordSet = new HashSet<>();
        chordSet.add(60);
        ChordResponse response = controller.handleChord(sessionIdTest, new ChordSubmission(chordSet));
        assertTrue(response.getIsChordProcessedSuccess());
    }

    @Test
    void test_try_play_past_end_of_game() {
        controller.handleCreateSession(sessionIdTest, new CreateSessionSubmission());
        controller.handleGameSettings(sessionIdTest, defaultSettings);
        controller.handleStartGame(sessionIdTest, new StartGameSubmission());

        Set<Integer> chordSet = new HashSet<>();
        chordSet.add(60);
        ChordResponse one = controller.handleChord(sessionIdTest, new ChordSubmission(chordSet));
        ChordResponse two = controller.handleChord(sessionIdTest, new ChordSubmission(chordSet));
        ChordResponse three = controller.handleChord(sessionIdTest, new ChordSubmission(chordSet));
        ChordResponse four = controller.handleChord(sessionIdTest, new ChordSubmission(chordSet));

        assertTrue(one.isSubmissionCorrect());
        assertTrue(two.isSubmissionCorrect());
        assertTrue(three.isSubmissionCorrect());
        assertFalse(four.getIsChordProcessedSuccess());
    }


    @Test
    void test_send_incorrect() {
        controller.handleCreateSession(sessionIdTest, new CreateSessionSubmission());
        controller.handleGameSettings(sessionIdTest, defaultSettings);
        controller.handleStartGame(sessionIdTest, new StartGameSubmission());

        Set<Integer> correct = new HashSet<>();
        correct.add(60);

        Set<Integer> wrong = new HashSet<>();
        wrong.add(61);

        ChordResponse one = controller.handleChord(sessionIdTest, new ChordSubmission(correct));
        ChordResponse two = controller.handleChord(sessionIdTest, new ChordSubmission(correct));
        ChordResponse three = controller.handleChord(sessionIdTest, new ChordSubmission(wrong));
        ChordResponse four = controller.handleChord(sessionIdTest, new ChordSubmission(wrong));
        ChordResponse five = controller.handleChord(sessionIdTest, new ChordSubmission(wrong));

        assertTrue(one.isSubmissionCorrect());
        assertTrue(two.isSubmissionCorrect());

        assertFalse(three.isSubmissionCorrect());
        assertFalse(four.isSubmissionCorrect());
        assertFalse(five.isSubmissionCorrect());

    }


    @Test
    void test_send_incorrect_then_all_correct() {
        controller.handleCreateSession(sessionIdTest, new CreateSessionSubmission());
        controller.handleGameSettings(sessionIdTest, defaultSettings);
        controller.handleStartGame(sessionIdTest, new StartGameSubmission());

        Set<Integer> correct = new HashSet<>();
        correct.add(60);

        Set<Integer> wrong = new HashSet<>();
        wrong.add(61);

        ChordResponse one = controller.handleChord(sessionIdTest, new ChordSubmission(correct));
        ChordResponse two = controller.handleChord(sessionIdTest, new ChordSubmission(correct));
        ChordResponse three = controller.handleChord(sessionIdTest, new ChordSubmission(wrong));
        ChordResponse four = controller.handleChord(sessionIdTest, new ChordSubmission(wrong));
        ChordResponse five = controller.handleChord(sessionIdTest, new ChordSubmission(correct));

        assertTrue(one.isSubmissionCorrect());
        assertTrue(two.isSubmissionCorrect());

        assertFalse(three.isSubmissionCorrect());
        assertFalse(four.isSubmissionCorrect());

        assertTrue(five.isSubmissionCorrect());

    }


}