package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Deque;

public class MusicMakerFactoryTest {

    @Test
    public void test_build_and_run_random_note_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(48)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(30);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        Deque<Chord> music = maker.makeMusic();

        Chord expectedLeft = new Chord(48);
        Chord expectedRight = new Chord(60);

        for (Chord curChord : music) {
            assertTrue(curChord.equals(expectedLeft) || curChord.equals(expectedRight));
        }
    }

    @Test
    public void test_build_and_run_random_interval_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.INTERVAL)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(1000);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        Deque<Chord> music = maker.makeMusic();

        System.out.println(music.toString());

        Chord expectedLeft = new Chord(48, 50);
        Chord expectedRight = new Chord(60, 62);

        // TODO
        for (Chord curChord : music) {
            assertTrue(curChord.equals(expectedLeft) || curChord.equals(expectedRight));
        }
    }


    @Test
    public void test_build_and_run_random_triad_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.TRIAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(1000);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        Deque<Chord> music = maker.makeMusic();

        System.out.println(music.toString());

        Chord expectedLeft = new Chord(48, 50);
        Chord expectedRight = new Chord(60, 62);

        // TODO
        for (Chord curChord : music) {
            assertTrue(curChord.equals(expectedLeft) || curChord.equals(expectedRight));
        }
    }
}
