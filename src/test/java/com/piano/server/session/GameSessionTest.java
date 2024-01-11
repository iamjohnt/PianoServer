package com.piano.server.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.session.GameSession;
import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import com.piano.server.stomp.response.ChordResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GameSessionTest {

    private String session_id;

    @BeforeEach
    void setup() {
        session_id = "qwerasdf1234";
    }

    @AfterEach
    void teardown() {
        session_id = null;
    }

    @Test
    void test_print_session() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE_INTERVAL_TRIAD_TETRAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(24)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(84)
                .setLength(10);

        GameSession session = new GameSession(this.session_id, config);
        session.startGame();
        System.out.println(session);
    }

    @Test
    void test_validate_chord_past_end_of_sequence() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(3);

        String session_id = "qwerasdf1234";
        GameSession session = new GameSession(session_id, config);
        session.startGame();

        System.out.println(session);

        ChordResponse result1 = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertTrue(result1.getIsCorrect());

        System.out.println(session);

        ChordResponse result2 = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertTrue(result2.getIsCorrect());

        ChordResponse result3 = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertTrue(result3.getIsCorrect());

        ChordResponse result4 = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertFalse(result4.getChordProcessedSuccess());

    }

    @Test
    void test_validate_chord_when_game_not_started_yet() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(3);

        GameSession session = new GameSession(session_id, config);

        ChordResponse result = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertEquals(result.getCorrectChord(), null);
    }

    @Test
    void test_submit_wrong_chord() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(1);

        GameSession session = new GameSession(session_id, config);
        session.startGame();

        ChordResponse result = session.checkChordAdvanceIfCorrect(new Chord(61));
        assertFalse(result.getIsCorrect());
    }

    @Test
    void test_end_game() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(3);

        GameSession session = new GameSession(session_id, config);
        session.startGame();

        session.checkChordAdvanceIfCorrect(new Chord(60));
        session.checkChordAdvanceIfCorrect(new Chord(60));

        session.endGame();

        ChordResponse response = session.checkChordAdvanceIfCorrect(new Chord(60));
        assertEquals(response.getCorrectChord(), null);
    }

}

