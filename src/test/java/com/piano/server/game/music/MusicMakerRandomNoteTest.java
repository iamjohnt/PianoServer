package com.piano.server.game.music;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakerRandomNote;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

public class MusicMakerRandomNoteTest {

    @Test
    public void test_left_hand_only() {
        Config config = new Config()
            .setKeySigNote(KeySigNote.G)
            .setKeySigMode(KeySigMode.MINOR)
            .setHands(WhichHands.LEFT)
            .setLength(1)
            .setLeftMin(21)
            .setLeftMax(21)
            .setRightMin(108)
            .setRightMax(108);

        MusicMakerRandomNote maker = new MusicMakerRandomNote(config);
        Deque<Chord> music = maker.makeMusic();

        Chord expected = new Chord(21);
        Chord actual = music.pop();

        assertEquals(expected, actual);
    }

    @Test
    public void test_right_hand_only() {
        Config config = new Config()
                .setKeySigNote(KeySigNote.D_FLAT)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setLength(1)
                .setLeftMin(21)
                .setLeftMax(21)
                .setRightMin(108)
                .setRightMax(108);

        MusicMakerRandomNote maker = new MusicMakerRandomNote(config);
        Deque<Chord> music = maker.makeMusic();

        Chord expected = new Chord(108);
        Chord actual = music.pop();

        assertEquals(expected, actual);
    }


}
