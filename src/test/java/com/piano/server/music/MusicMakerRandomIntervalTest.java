package com.piano.server.music;

import com.piano.server.game.music.*;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicMakerRandomIntervalTest {

    private Config config;

    @BeforeEach
    public void setup() {
        config = new Config()
            .setHands(WhichHands.RIGHT)
            .setLength(1);
    }

    @AfterEach
    public void tearDown() {
        config = null;
    }


    @Test
    public void test_interval_at_top_of_piano() {
        config.setKeySigNote(KeySigNote.C)
            .setKeySigMode(KeySigMode.MAJOR)
            .setRightMin(107)
            .setRightMax(108);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Chord expected = new Chord(107, 108);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }


    @Test
    public void test_tight_interval_at_bottom() {
        config.setKeySigNote(KeySigNote.C)
            .setKeySigMode(KeySigMode.MAJOR)
            .setRightMin(21)
            .setRightMax(23);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Chord expected = new Chord(21, 23);
        Chord actual = maker.makeMusic().pop();
        assertEquals(expected, actual);
    }


    @Test
    public void test_interval_left_hands() {
        config.setKeySigNote(KeySigNote.B)
            .setKeySigMode(KeySigMode.MAJOR)
            .setHands(WhichHands.LEFT)
            .setLength(1)
            .setLeftMin(23)
            .setLeftMax(25)
            .setRightMin(107)
            .setRightMax(108);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Deque<Chord> music = maker.makeMusic();
        Chord expected = new Chord(23, 25);
        Chord actual = music.pop();
        assertEquals(expected, actual);
    }


    @Test
    public void test_interval_right_hand() {
        config.setKeySigNote(KeySigNote.F)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setLength(1)
                .setLeftMin(-1)
                .setLeftMax(-1)
                .setRightMin(65)
                .setRightMax(67);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Deque<Chord> music = maker.makeMusic();
        Chord expected = new Chord(65, 67);
        Chord actual = music.pop();
        assertEquals(expected, actual);
    }

    @Test
    public void test_interval_both_hands() {
        config.setKeySigNote(KeySigNote.D)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLength(20)
                .setLeftMin(30)
                .setLeftMax(40)
                .setRightMin(60)
                .setRightMax(70);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Deque<Chord> music = maker.makeMusic();

        boolean areIntervalsInBounds = true;
        for (int i = 0; i < 20; i++) {
            Chord chord = music.pop();
            if (!chord.isInBounds(30, 40) && !chord.isInBounds(60, 70)) {
                areIntervalsInBounds = false;
            }
        }
        assertEquals(true, areIntervalsInBounds);
    }




    @Test
    public void test_interval_within_bounds() {
        config.setKeySigNote(KeySigNote.C)
            .setKeySigMode(KeySigMode.MINOR)
            .setLength(500)
            .setLeftMin(36)
            .setLeftMax(48)
            .setRightMin(60)
            .setRightMax(72);

        MusicMakable maker = new MusicMakerRandomInterval(config);
        Deque<Chord> music = maker.makeMusic();

        boolean areIntervalsInBounds = true;
        for (int i = 0; i < 500; i++) {
            Chord chord = music.pop();
            List<Integer> chordNotes = chord.getChordList();

            int botNote = chordNotes.get(0);
            int topNote = chordNotes.get(1);

            boolean isBotNoteOutOfBounds = botNote < 36 || (botNote >= 48 && botNote < 60) || botNote >= 72;
            boolean isTopNoteOutOfBounds = topNote <= 36 || (topNote > 48 && topNote <= 60) || topNote > 72;

            if (isBotNoteOutOfBounds || isTopNoteOutOfBounds) {
                areIntervalsInBounds = false;
            }
        }

        assert(areIntervalsInBounds);
    }
}
