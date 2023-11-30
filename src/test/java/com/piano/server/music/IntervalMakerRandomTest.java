package com.piano.server.music;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.ChromaticNotesList;
import com.piano.server.game.music.IntervalMakerRandom;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class IntervalMakerRandomTest {

    @Test
    public void test_interval_at_top_of_piano() {
        ChromaticNotesList cmajor = new ChromaticNotesList(KeySigNote.C, KeySigMode.MAJOR);
        IntervalMakerRandom maker = new IntervalMakerRandom(cmajor, 107, 108);
        Chord expected = new Chord(107, 108);
        Chord actual = maker.createChord();
        assertEquals(expected, actual);
    }

    @Test
    public void test_tight_interval_at_bottom() {
        ChromaticNotesList cmajor = new ChromaticNotesList(KeySigNote.A, KeySigMode.MAJOR);
        IntervalMakerRandom maker = new IntervalMakerRandom(cmajor, 21, 23);
        Chord expected = new Chord(21, 23);
        Chord actual = maker.createChord();
        assertEquals(expected, actual);
    }

    @Test
    public void test_tight_interval_at_middle() {
        ChromaticNotesList cmajor = new ChromaticNotesList(KeySigNote.E_FLAT, KeySigMode.MINOR);
        IntervalMakerRandom maker = new IntervalMakerRandom(cmajor, 63, 65);
        Chord expected = new Chord(63, 65);
        Chord actual = maker.createChord();
        assertEquals(expected, actual);
    }

    @Test
    public void test_interval_always_in_bounds() {
//        ChromaticNotesList cmajor = new ChromaticNotesList(KeySigNote.C, KeySigMode.MINOR);
//        IntervalMakerRandom randMaker = new IntervalMakerRandom(cmajor, 60, 72);
//
//        boolean isInBounds = true;
//        Chord sampleChord;
//        for (int i = 0; i < 100; i++) {
//            sampleChord = randMaker.createChord();
//            if ()
//        }
//        System.out.println(sampleChords.toString());
    }
}
