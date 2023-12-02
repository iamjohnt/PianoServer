package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicMakerRandomTriadTest {


    @Test
    void test_create_a_flat_major() {
        Config config = new Config()
                .setKeySigNote(KeySigNote.A_FLAT)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setLength(1)
                .setLeftMin(-1)
                .setRightMin(-1)
                .setRightMin(68)
                .setRightMax(75);

        MusicMakerRandomTriad maker = new MusicMakerRandomTriad(config);
        Chord expected = new Chord(68, 72, 75);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }


    @Test
    void test_triad_at_top() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.F)
            .setKeySigMode(KeySigMode.MAJOR)
            .setHands(WhichHands.RIGHT)
            .setLength(1)
            .setRightMin(101)
            .setRightMax(108);

        MusicMakerRandomTriad maker = new MusicMakerRandomTriad(config);
        Chord expected = new Chord(101, 105, 108);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }

    @Test
    void test_triad_at_bottom() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.A)
            .setKeySigMode(KeySigMode.MINOR)
            .setHands(WhichHands.LEFT)
            .setLength(1)
            .setLeftMin(21)
            .setLeftMax(28);

        MusicMakerRandomTriad maker = new MusicMakerRandomTriad(config);
        Chord expected = new Chord(21, 24, 28);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }

    @Test
    void test_triad_at_middle() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.C_SHARP)
            .setKeySigMode(KeySigMode.MAJOR)
            .setHands(WhichHands.RIGHT)
            .setLength(1)
            .setLeftMin(-1)
            .setLeftMax(-1)
            .setRightMin(49)
            .setRightMax(56);

        MusicMakerRandomTriad maker = new MusicMakerRandomTriad(config);
        Chord expected = new Chord(49, 53, 56);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }

    @Test
    public void test_triads_both_hands() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.C_SHARP)
            .setKeySigMode(KeySigMode.MAJOR)
            .setHands(WhichHands.BOTH)
            .setLength(20)
            .setLeftMin(61)
            .setLeftMax(68)
            .setRightMin(73)
            .setRightMax(80);

        MusicMakable maker = new MusicMakerRandomTriad(config);
        Deque<Chord> music = maker.makeMusic();
        Chord left = new Chord(61, 65, 68);
        Chord right = new Chord(73, 77, 80);
        boolean hasBothRightAndLeft = music.contains(left) && music.contains(right);
        assertTrue(hasBothRightAndLeft);
    }


    @Test
    void test_triads_within_bounds() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.F_SHARP)
            .setKeySigMode(KeySigMode.MINOR)
            .setHands(WhichHands.BOTH)
            .setLength(500)
            .setLeftMin(36)
            .setLeftMax(48)
            .setRightMin(60)
            .setRightMax(72);

        MusicMakable maker = new MusicMakerRandomTriad(config);
        Deque<Chord> music = maker.makeMusic();

        boolean areTriadsWithinBounds = true;
        for (int i = 0; i < 500; i++) {
            Chord chord = music.pop();
            List<Integer> chordNotes = chord.getChordList();

            int botNote = chordNotes.get(0);
            int topNote = chordNotes.get(1);

            boolean isBotNoteOutOfBounds = botNote < 36 || (botNote >= 48 && botNote < 60) || botNote >= 72;
            boolean isTopNoteOutOfBounds = topNote <= 36 || (topNote > 48 && topNote <= 60) || topNote > 72;

            if (isBotNoteOutOfBounds || isTopNoteOutOfBounds) {
                areTriadsWithinBounds = false;
            }
        }
    }


}