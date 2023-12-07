package com.piano.server.stomp;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSettingsSubmissionValidatorTest {

    private GameSettingsSubmissionValidator validator;

    @BeforeEach

    void setup() {
        validator = new GameSettingsSubmissionValidator();
    }

    void teardown() {
        validator = new GameSettingsSubmissionValidator();
    }

    @Test
    void test_null() {
        GameSettingsSubmission settings = new GameSettingsSubmission();
        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_left_min_too_low() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.LEFT)
                .setLength(50)
                .setLeftMin(20)
                .setLeftMax(60)
                .setRightMin(50)
                .setRightMax(60);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_left_max_too_high() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.LEFT)
                .setLength(50)
                .setLeftMin(60)
                .setLeftMax(109)
                .setRightMin(50)
                .setRightMax(60);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_right_mmin_too_low() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.RIGHT)
                .setLength(50)
                .setLeftMin(60)
                .setLeftMax(70)
                .setRightMin(20)
                .setRightMax(80);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_right_max_too_high() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.RIGHT)
                .setLength(50)
                .setLeftMin(60)
                .setLeftMax(70)
                .setRightMin(50)
                .setRightMax(109);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_left_crossed() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.LEFT)
                .setLength(50)
                .setLeftMin(55)
                .setLeftMax(54)
                .setRightMin(50)
                .setRightMax(109);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_right_crossed() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.BOTH)
                .setLength(50)
                .setLeftMin(60)
                .setLeftMax(70)
                .setRightMin(55)
                .setRightMax(54);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_range_for_note_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.RIGHT)
                .setLength(50)
                .setLeftMin(0)
                .setLeftMax(0)
                .setRightMin(55)
                .setRightMax(55);

        boolean result = validator.isValidGameSettings(settings);
        assertTrue(result);
    }

    @Test
    void test_range_for_interval_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.INTERVAL)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.RIGHT)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(47)
                .setRightMin(33)
                .setRightMax(77);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_range_for_triad_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.TRIAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.RIGHT)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(80)
                .setRightMin(52)
                .setRightMax(60);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_range_for_tetrad_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.TETRAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.BOTH)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(47)
                .setRightMin(33)
                .setRightMax(88);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_range_for_note_interval_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE_INTERVAL)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.BOTH)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(47)
                .setRightMin(33)
                .setRightMax(88);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }


    @Test
    void test_range_for_note_interval_triad_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE_INTERVAL_TRIAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.BOTH)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(47)
                .setRightMin(33)
                .setRightMax(88);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }

    @Test
    void test_range_for_note_interval_triad_tetrad_game() {
        GameSettingsSubmission settings = new GameSettingsSubmission()
                .setChordPool(ChordPool.NOTE_INTERVAL_TRIAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setWhichHands(WhichHands.BOTH)
                .setLength(50)
                .setLeftMin(36)
                .setLeftMax(47)
                .setRightMin(33)
                .setRightMax(88);

        boolean result = validator.isValidGameSettings(settings);
        assertFalse(result);
    }





}
