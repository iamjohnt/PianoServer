package com.piano.server;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
import com.piano.server.game.music.MusicMakerFactoryConfig;
import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigToSession {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void from_config_to_music_sequence() {
        System.out.println("======================================== testMusicMaker ========================================");
        System.out.println("description: config -> session -> factory -> maker -> final music");

        // create music maker config
        MusicMakerFactoryConfig config = new MusicMakerFactoryConfig(
                KeySigNote.C,
                KeySigMode.MINOR,
                ChordPool.TRIAD,
                WhichHands.BOTH
        );

        // based on config, create appropriate music maker
        MusicMakerFactory factory = new MusicMakerFactory(config);
        MusicMakable maker = factory.getMusicMaker();

        // make the music
        Deque<Chord> musicSeq = maker.makeMusic();

        // assert
        Chord expected = new Chord(60, 64, 67);
        Chord actual = musicSeq.pop();
        System.out.println("expected first chord: " + expected.toString());
        System.out.println("actual first chord:   " + actual.toString());
        assertEquals(expected, actual);
    }



}
