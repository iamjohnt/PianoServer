package com.piano.server.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.session.GameSession;
import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GameSessionTest {

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

        String session_id = "qwerasdf1234";
        GameSession session = new GameSession(session_id, config);
        session.startGame();
        System.out.println(session);
    }

    @Test
    void test_validate_chord() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(1);

        String session_id = "qwerasdf1234";
        GameSession session = new GameSession(session_id, config);
        session.startGame();

        boolean result = session.validateChord(new Chord(60));
        assertTrue(result);
    }

}

