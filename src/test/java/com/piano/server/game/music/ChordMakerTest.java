package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChordMakerTest {

    private ChromaticNotesList mode;
    private ChordMaker maker;

    @BeforeEach
    void setup() {
        mode = new ChromaticNotesList(KeySigNote.C, KeySigMode.MAJOR);
    }

    @AfterEach
    void teardown() {
        mode = null;
    }

    @Test
    void test_create_note() {
        maker = new ChordMaker(mode, ChordMaker.NOTE);
        Chord expected = new Chord(60);
        Chord actual = maker.createChord(60);
        assertEquals(expected, actual);
    }

    @Test
    void test_create_intervals() {
        maker = new ChordMaker(mode, ChordMaker.INTERVAL_SECOND);
        Chord expectedSecond = new Chord(60, 62);
        Chord actualSecond = maker.createChord(60);
        assertEquals(expectedSecond, actualSecond);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_THIRD);
        Chord expectedThird = new Chord(60, 64);
        Chord actualThird = maker.createChord(60);
        assertEquals(expectedThird, actualThird);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_FOURTH);
        Chord expectedFourth = new Chord(60, 65);
        Chord actualFourth = maker.createChord(60);
        assertEquals(expectedFourth, actualFourth);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_FIFTH);
        Chord expectedFifth = new Chord(60, 67);
        Chord actualFifth = maker.createChord(60);
        assertEquals(expectedFourth, actualFourth);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_SIXTH);
        Chord expectedSixth = new Chord(60, 69);
        Chord actualSixth = maker.createChord(60);
        assertEquals(expectedFourth, actualFourth);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_SEVENTH);
        Chord expected7 = new Chord(60, 71);
        Chord actual7 = maker.createChord(60);
        assertEquals(expectedFourth, actualFourth);

        maker = new ChordMaker(mode, ChordMaker.INTERVAL_OCTAVE);
        Chord expected8 = new Chord(60, 72);
        Chord actual8 = maker.createChord(60);
        assertEquals(expectedFourth, actualFourth);
    }

    @Test
    void test_create_triads() {
        maker = new ChordMaker(mode, ChordMaker.TRIAD);
        Chord expectedTriad = new Chord(60, 64, 67);
        Chord actualTriad = maker.createChord(60);
        assertEquals(expectedTriad, actualTriad);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_SUS_2);
        Chord expectedTriadSus2 = new Chord(60, 62, 67);
        Chord actualTriadSus2 = maker.createChord(60);
        assertEquals(expectedTriadSus2, actualTriadSus2);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_SUS_4);
        Chord expected = new Chord(60, 65, 67);
        Chord actual = maker.createChord(60);
        assertEquals(expected, actual);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_INVERSION_2_DOWN);
        Chord expectedTriadInversion2Down = new Chord(52, 55, 60);
        Chord actualTriadInversion2Down = maker.createChord(60);
        assertEquals(expectedTriadInversion2Down, actualTriadInversion2Down);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_INVERSION_2_UP);
        Chord expectedTriadInversion2Up = new Chord(64, 67, 72);
        Chord actualTriadInversion2Up = maker.createChord(60);
        assertEquals(expectedTriadInversion2Up, actualTriadInversion2Up);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_INVERSION_3_DOWN);
        Chord EXPECTED_TRIAD_INVERSION_3_DOWN = new Chord(55, 60, 64);
        Chord ACTUAL_TRIAD_INVERSION_3_DOWN = maker.createChord(60);
        assertEquals(EXPECTED_TRIAD_INVERSION_3_DOWN, ACTUAL_TRIAD_INVERSION_3_DOWN);

        maker = new ChordMaker(mode, ChordMaker.TRIAD_INVERSION_3_UP);
        Chord EXPECTED_TRIAD_INVERSION_3_UP = new Chord(67, 72, 76);
        Chord ACTUAL_TRIAD_INVERSION_3_UP = maker.createChord(60);
        assertEquals(EXPECTED_TRIAD_INVERSION_3_UP, ACTUAL_TRIAD_INVERSION_3_UP);
    }

    @Test
    void test_create_tetrad_seventh() {

        maker = new ChordMaker(mode,ChordMaker.TETRAD);
        Chord expected = new Chord(60, 64, 67, 72);
        Chord actual = maker.createChord(60);
        assertEquals(expected, actual);

        maker = new ChordMaker(mode,ChordMaker.TETRAD_SEVENTH);
        Chord expectedSeventh = new Chord(60, 64, 67, 71);
        Chord seactualSeventh = maker.createChord(60);
        assertEquals(expectedSeventh, seactualSeventh);

        maker = new ChordMaker(mode,ChordMaker.TETRAD_INVERTED);
        Chord expectedInverted = new Chord(55, 60, 64, 67);
        Chord actualInverted = maker.createChord(60);
        assertEquals(expectedInverted, actualInverted);
    }

}


