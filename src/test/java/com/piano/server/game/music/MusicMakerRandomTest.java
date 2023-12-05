package com.piano.server.game.music;

import com.piano.server.game.util.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Deque;

public class MusicMakerRandomTest {

    private Config baseConfig;

    @BeforeEach
    void setup() {
        baseConfig = new Config()
            .setKeySigNote(KeySigNote.C)
            .setKeySigMode(KeySigMode.MAJOR)
            .setHands(WhichHands.RIGHT)
            .setLength(20);
    }

    @AfterEach
    void teardown() {
        baseConfig = null;
    }

    /*
    what correctness do i need to test?
    if left hand, then left hand only -- done
    if right hand, then right hand only -- done
    if both hands, then has both hands -- done
    if notes, then notes only -- done
    if intervals, then intervals only
    if triads, then triads only
    if tetrads, then tetrads only
    if note interval, then has them
    if note interval triad, then has them
    if note interval triad tetrad, then has them
    correct length
    test top piano, bottom piano boundaries for all chords


    */

    @Test
    void test_note() {

        Config config = new Config()
                .setChordPool(ChordPool.NOTE)
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(60)
                .setLength(1);

        ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
        ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.NOTE);
        ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
        ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

        MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                .setWhichHands(config.getHands())
                .setLmin(config.getLeftMin())
                .setLmax(config.getLeftMax())
                .setRmin(config.getRightMin())
                .setRmax(config.getRightMax())
                .setLength(config.getLength())
                .setChordMakerPool(pool);

        Deque<Chord> music = randomNoteMaker.makeMusic();

        System.out.println(music);
    }

    @Nested
    @DisplayName("test intervals")
    class Intervals {
        @Test
        void test_interval_second() {

            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(62)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_SECOND);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 62);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_third() {

            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(64)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_THIRD);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 64);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_fourth() {

            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(65)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_FOURTH);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 65);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_fifth() {

            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(67)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_FIFTH);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 67);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_sixth() {
            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(69)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_SIXTH);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 69);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_seventh() {
            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(71)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_SEVENTH);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 71);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_interval_octave() {
            Config config = new Config()
                    .setChordPool(ChordPool.INTERVAL)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(72)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.INTERVAL_OCTAVE);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 72);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("test triads")
    class Triads {

        @Test
        void test_triad_normal() {

            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(67)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TRIAD);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 64, 67);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_sus_2() {

            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(67)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_2);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 62, 67);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_sus_4() {

            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(67)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_4);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 65, 67);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_inversion_2_down() {

            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(64)
                    .setRightMax(72)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_DOWN);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(64, 67, 72);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_inversion_2_up() {

            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(64)
                    .setRightMax(72)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_UP);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(64, 67, 72);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_inversion_3_down() {
            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(69)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_DOWN);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 65, 69);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_triad_inversion_3_up() {
            Config config = new Config()
                    .setChordPool(ChordPool.TRIAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(69)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_UP);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 65, 69);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }
    }

    @Nested
    @DisplayName("test tetrads")
    class Tetrads {

        @Test
        void test_tetrad_normal() {

            Config config = new Config()
                    .setChordPool(ChordPool.TETRAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(72)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TETRAD);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 64, 67, 72);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_tetrad_inverted() {
            Config config = new Config()
                    .setChordPool(ChordPool.TETRAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(72)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TETRAD_INVERTED);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 65, 69, 72);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }

        @Test
        void test_tetrad_seventh() {
            Config config = new Config()
                    .setChordPool(ChordPool.TETRAD)
                    .setKeySigNote(KeySigNote.C)
                    .setKeySigMode(KeySigMode.MAJOR)
                    .setHands(WhichHands.RIGHT)
                    .setRightMin(60)
                    .setRightMax(71)
                    .setLength(1);

            ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());
            ChordMaker chordMaker = new ChordMaker(notePool, ChordPattern.TETRAD_SEVENTH);
            ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(chordMaker);
            ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

            MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                    .setWhichHands(config.getHands())
                    .setLmin(config.getLeftMin())
                    .setLmax(config.getLeftMax())
                    .setRmin(config.getRightMin())
                    .setRmax(config.getRightMax())
                    .setLength(config.getLength())
                    .setChordMakerPool(pool);

            Deque<Chord> music = randomNoteMaker.makeMusic();
            Chord expected = new Chord(60, 64, 67, 71);
            Chord actual = music.pop();
            assertEquals(expected, actual);
        }
    }

}
