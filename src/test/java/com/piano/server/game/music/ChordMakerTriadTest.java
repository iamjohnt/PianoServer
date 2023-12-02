package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChordMakerTriadTest {

    @Test
    void test_creating_a_chord_with_just_root() {
        ChordMakerTriad maker = new ChordMakerTriad(KeySigNote.D_SHARP, KeySigMode.MAJOR);
        Chord expected = new Chord(63, 67, 70);
        Chord actual = maker.makeChord(63);
        assertEquals(expected, actual);
    }

    @Test
    void test_for_null_upon_pass_out_of_bounds_note() {
        ChordMakerTriad maker = new ChordMakerTriad(KeySigNote.E, KeySigMode.MINOR);
        Chord expected = null;
        Chord actual = maker.makeChord(1000);
        assertEquals(expected, actual);

        Chord actual2 = maker.makeChord(65);
        assertEquals(expected, actual2);

        Chord actual3 = maker.makeChord(20);
        assertEquals(expected, actual3);
    }
}
