package com.piano.server.game.music;

import com.piano.server.game.util.ChordPattern;
import com.piano.server.game.util.ChordPool;

import java.util.List;

// based on config, output appropriate music generator
public class MusicMakerFactory {

    public MusicMakerFactory() {
    }

    public MusicMakable buildMusicMaker(Config config) {
        ChordPool pool = config.getChordPool();

        MusicMakable musicMaker = null;

        switch (pool) {
            case NOTE -> musicMaker = buildRandomNoteMaker(config);
            case INTERVAL -> musicMaker = buildRandomIntervalMaker(config);
            case TRIAD -> musicMaker = buildRandomTriadMaker(config);
//            case TETRAD ->
//            case NOTE_INTERVAL
//            case NOTE_INTERVAL_TRIAD
//            case NOTE_INTERVAL_TRIAD_TETRAD
        }

        return musicMaker;
    }


    private MusicMakerRandom buildRandomNoteMaker(Config c) {
        ChromaticNotesList notePool = new ChromaticNotesList(c.getKeySigNote(), c.getKeySigMode());
        ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.NOTE);
        ChordMakerGroup noteMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
        ChordMakerPool pool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);

        MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                .setWhichHands(c.getHands())
                .setLmin(c.getLeftMin())
                .setLmax(c.getLeftMax())
                .setRmin(c.getRightMin())
                .setRmax(c.getRightMax())
                .setLength(c.getLength())
                .setChordMakerPool(pool);

        return randomNoteMaker;
    }

    private MusicMakerRandom buildRandomIntervalMaker(Config c) {

        ChromaticNotesList notePool = new ChromaticNotesList(c.getKeySigNote(), c.getKeySigMode());

        ChordMaker SECOND = new ChordMaker(notePool, ChordPattern.INTERVAL_SECOND);
        ChordMaker THIRD = new ChordMaker(notePool, ChordPattern.INTERVAL_THIRD);
        ChordMaker FOURTH = new ChordMaker(notePool, ChordPattern.INTERVAL_FOURTH);
        ChordMaker FIFTH = new ChordMaker(notePool, ChordPattern.INTERVAL_FIFTH);
        ChordMaker SIXTH = new ChordMaker(notePool, ChordPattern.INTERVAL_SIXTH);
        ChordMaker SEVENTH = new ChordMaker(notePool, ChordPattern.INTERVAL_SEVENTH);
        ChordMaker OCTAVE = new ChordMaker(notePool, ChordPattern.INTERVAL_OCTAVE);

        ChordMakerGroup intervalMakerGroup = new ChordMakerGroup(
                List.of(SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, OCTAVE)
        );

        ChordMakerPool pool = new ChordMakerPool()
                .addChordMakerGroup(intervalMakerGroup);

        MusicMakerRandom randomIntervalMaker = new MusicMakerRandom()
                .setWhichHands(c.getHands())
                .setLmin(c.getLeftMin())
                .setLmax(c.getLeftMax())
                .setRmin(c.getRightMin())
                .setRmax(c.getRightMax())
                .setLength(c.getLength())
                .setChordMakerPool(pool);

        return randomIntervalMaker;
    }

    private MusicMakerRandom buildRandomTriadMaker(Config c) {

        ChromaticNotesList notePool = new ChromaticNotesList(c.getKeySigNote(), c.getKeySigMode());

        ChordMaker triad1 = new ChordMaker(notePool, ChordPattern.TRIAD);
        ChordMaker triad2 = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_2);
        ChordMaker triad3 = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_4);
        ChordMaker triad4 = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_DOWN);
        ChordMaker triad5 = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_UP);
        ChordMaker triad6 = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_DOWN);
        ChordMaker triad7 = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_UP);

        ChordMakerGroup intervalMakerGroup = new ChordMakerGroup(
                List.of(triad1, triad2, triad3, triad4, triad5, triad6, triad7)
        );

        ChordMakerPool pool = new ChordMakerPool()
                .addChordMakerGroup(intervalMakerGroup);

        MusicMakerRandom randomIntervalMaker = new MusicMakerRandom()
                .setWhichHands(c.getHands())
                .setLmin(c.getLeftMin())
                .setLmax(c.getLeftMax())
                .setRmin(c.getRightMin())
                .setRmax(c.getRightMax())
                .setLength(c.getLength())
                .setChordMakerPool(pool);

        return randomIntervalMaker;
    }

}
