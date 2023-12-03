package com.piano.server.game.music;

import com.piano.server.game.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Deque;

public class MusicMakerRandomTest {

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void teardown() {

    }

    /*
    what correctness do i need to test?
    if left hand, then left hand only
    if right hand, then right hand only
    if both hands, then has both hands
    if notes, then notes only
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
    void test_music_makers_with_notes_and_intervals() {

        ChromaticNotesList c_major = new ChromaticNotesList(KeySigNote.C, KeySigMode.MAJOR);
        Config config = new Config()
                .setKeySigNote(KeySigNote.C)
                .setKeySigMode(KeySigMode.MAJOR)
                .setHands(WhichHands.RIGHT)
                .setRightMin(60)
                .setRightMax(64)
                .setLength(20);

        MusicMakerRandom random = new MusicMakerRandom(config);

        // make individual chord makers
        ChordMaker noteMaker = new ChordMaker(c_major, ChordPattern.NOTE);
        ChordMaker intervalSecondMaker = new ChordMaker(c_major, ChordPattern.INTERVAL_SECOND);
        ChordMaker intervalThirdMaker = new ChordMaker(c_major, ChordPattern.INTERVAL_THIRD);

        // add them to their respective groups
        ChordMakerGroup noteMakerGroup = new ChordMakerGroup();
        noteMakerGroup.addChordMaker(noteMaker);

        ChordMakerGroup intervalMakerGroup = new ChordMakerGroup();
        intervalMakerGroup.addChordMaker(intervalSecondMaker);
        intervalMakerGroup.addChordMaker(intervalThirdMaker);

        // add the groups to the pool
        ChordMakerPool pool = new ChordMakerPool();
        pool.addChordMakerGroup(intervalMakerGroup);
        pool.addChordMakerGroup(noteMakerGroup);

        // add pool to the music maker
        random.setChordMakerPool(pool);

        // make music
        Deque<Chord> music = random.makeMusic();
        System.out.println(music);

    }


}
