package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.List;

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
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

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
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        for (Chord curChord : music) {
            assertTrue(curChord.getChordSet().size() == 2);
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
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        for (Chord curChord : music) {
            assertTrue(curChord.getChordSet().size() == 3);
        }
    }

    @Test
    public void test_build_and_run_random_tetrad_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.TETRAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        for (Chord curChord : music) {
            assertTrue(curChord.getChordSet().size() == 4);
        }
    }

    @Test
    public void test_build_and_run_random_note_inteveral_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE_INTERVAL)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        boolean hasNotes = false;
        boolean hasIntervals = false;
        for (Chord curChord : music) {
            if (curChord.getChordSet().size() == 1) {
                hasNotes = true;
            }
            if (curChord.getChordSet().size() == 2) {
                hasIntervals = true;
            }
        }
        assertTrue(hasNotes && hasIntervals);
    }


    @Test
    public void test_build_and_run_random_note_inteveral_triad_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE_INTERVAL_TRIAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        boolean hasNotes = false;
        boolean hasIntervals = false;
        boolean hasTriads = false;
        for (Chord curChord : music) {
            if (curChord.getChordSet().size() == 1) {
                hasNotes = true;
            }
            if (curChord.getChordSet().size() == 2) {
                hasIntervals = true;
            }
            if (curChord.getChordSet().size() == 3) {
                hasTriads = true;
            }
        }

        System.out.println(music.toString());
        assertTrue(hasNotes && hasIntervals && hasTriads);
    }

    @Test
    public void test_build_and_run_random_note_inteveral_triad_tetrad_maker() {
        Config config = new Config()
                .setChordPool(ChordPool.NOTE_INTERVAL_TRIAD_TETRAD)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.BOTH)
                .setLeftMin(36)
                .setLeftMax(48)
                .setRightMin(60)
                .setRightMax(72)
                .setLength(100);

        MusicMakerFactory factory = new MusicMakerFactory();
        MusicMakable maker = factory.buildMusicMaker(config);
        List<Chord> music = maker.makeMusic();

        boolean hasNotes = false;
        boolean hasIntervals = false;
        boolean hasTriads = false;
        boolean hasTetrads = false;

        for (Chord curChord : music) {
            if (curChord.getChordSet().size() == 1) {
                hasNotes = true;
            }
            if (curChord.getChordSet().size() == 2) {
                hasIntervals = true;
            }
            if (curChord.getChordSet().size() == 3) {
                hasTriads = true;
            }
            if (curChord.getChordSet().size() == 4) {
                hasTetrads = true;
            }
        }
        assertTrue(hasNotes && hasIntervals && hasTriads && hasTetrads);
    }



}
