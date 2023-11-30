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
