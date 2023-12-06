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
        ChromaticNotesList notePool = new ChromaticNotesList(config.getKeySigNote(), config.getKeySigMode());

        MusicMakable musicMaker = null;
        switch (pool) {
            case NOTE -> {
                ChordMakerGroup noteMakerGroup = buildNoteMakerGroup(notePool);
                ChordMakerPool chordMakerPool = new ChordMakerPool().addChordMakerGroup(noteMakerGroup);
                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case INTERVAL -> {
                ChordMakerGroup intervalMakerGroup = buildIntervalMakerGroup(notePool);
                ChordMakerPool chordMakerPool = new ChordMakerPool().addChordMakerGroup(intervalMakerGroup);
                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case TRIAD -> {
                ChordMakerGroup triadMakerGroup = buildTriadMakerGroup(notePool);
                ChordMakerPool chordMakerPool = new ChordMakerPool().addChordMakerGroup(triadMakerGroup);
                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case TETRAD -> {
                ChordMakerGroup tetradMakerGroup = buildTetradMakerGroup(notePool);
                ChordMakerPool chordMakerPool = new ChordMakerPool().addChordMakerGroup(tetradMakerGroup);
                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case NOTE_INTERVAL -> {
                ChordMakerGroup noteMakerGroup = buildNoteMakerGroup(notePool);
                ChordMakerGroup intervalMakerGroup = buildIntervalMakerGroup(notePool);
                ChordMakerPool chordMakerPool = new ChordMakerPool()
                        .addChordMakerGroup(noteMakerGroup)
                        .addChordMakerGroup(intervalMakerGroup);

                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case NOTE_INTERVAL_TRIAD -> {
                ChordMakerGroup noteMakerGroup = buildNoteMakerGroup(notePool);
                ChordMakerGroup intervalMakerGroup = buildIntervalMakerGroup(notePool);
                ChordMakerGroup triadMakerGroup = buildTriadMakerGroup(notePool);

                ChordMakerPool chordMakerPool = new ChordMakerPool()
                        .addChordMakerGroup(noteMakerGroup)
                        .addChordMakerGroup(intervalMakerGroup)
                        .addChordMakerGroup(triadMakerGroup);

                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
            case NOTE_INTERVAL_TRIAD_TETRAD -> {
                ChordMakerGroup noteMakerGroup = buildNoteMakerGroup(notePool);
                ChordMakerGroup intervalMakerGroup = buildIntervalMakerGroup(notePool);
                ChordMakerGroup triadMakerGroup = buildTriadMakerGroup(notePool);
                ChordMakerGroup tetradMakerGroup = buildTetradMakerGroup(notePool);

                ChordMakerPool chordMakerPool = new ChordMakerPool()
                        .addChordMakerGroup(noteMakerGroup)
                        .addChordMakerGroup(intervalMakerGroup)
                        .addChordMakerGroup(triadMakerGroup)
                        .addChordMakerGroup(tetradMakerGroup);

                MusicMakerRandom randMaker = createBaseMusicMakerRandom(config);
                randMaker.setChordMakerPool(chordMakerPool);
                musicMaker = randMaker;
            }
        }
        return musicMaker;
    }

    private MusicMakerRandom createBaseMusicMakerRandom(Config c) {
        MusicMakerRandom randomNoteMaker = new MusicMakerRandom()
                .setWhichHands(c.getHands())
                .setLmin(c.getLeftMin())
                .setLmax(c.getLeftMax())
                .setRmin(c.getRightMin())
                .setRmax(c.getRightMax())
                .setLength(c.getLength());
        return randomNoteMaker;
    }

    private ChordMakerGroup buildNoteMakerGroup(ChromaticNotesList notePool) {
        ChordMaker noteMaker = new ChordMaker(notePool, ChordPattern.NOTE);
        ChordMakerGroup chordMakerGroup = new ChordMakerGroup().addChordMaker(noteMaker);
        return chordMakerGroup;
    }

    private ChordMakerGroup buildIntervalMakerGroup(ChromaticNotesList notePool) {
        ChordMaker SECOND = new ChordMaker(notePool, ChordPattern.INTERVAL_SECOND);
        ChordMaker THIRD = new ChordMaker(notePool, ChordPattern.INTERVAL_THIRD);
        ChordMaker FOURTH = new ChordMaker(notePool, ChordPattern.INTERVAL_FOURTH);
        ChordMaker FIFTH = new ChordMaker(notePool, ChordPattern.INTERVAL_FIFTH);
        ChordMaker SIXTH = new ChordMaker(notePool, ChordPattern.INTERVAL_SIXTH);
        ChordMaker SEVENTH = new ChordMaker(notePool, ChordPattern.INTERVAL_SEVENTH);
        ChordMaker OCTAVE = new ChordMaker(notePool, ChordPattern.INTERVAL_OCTAVE);
        ChordMakerGroup chordMakerGroup = new ChordMakerGroup(List.of(SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, OCTAVE));
        return chordMakerGroup;
    }

    private ChordMakerGroup buildTriadMakerGroup(ChromaticNotesList notePool) {
        ChordMaker triad = new ChordMaker(notePool, ChordPattern.TRIAD);
        ChordMaker triad_sus_2 = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_2);
        ChordMaker triad_sus_4 = new ChordMaker(notePool, ChordPattern.TRIAD_SUS_4);
        ChordMaker triad_inversion_2_down = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_DOWN);
        ChordMaker triad_inversion_2_up = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_2_UP);
        ChordMaker triad_inversion_3_down = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_DOWN);
        ChordMaker triad_inversion_3_up = new ChordMaker(notePool, ChordPattern.TRIAD_INVERSION_3_UP);
        ChordMakerGroup chordMakerGroup = new ChordMakerGroup(
                List.of(triad, triad_sus_2, triad_sus_4, triad_inversion_2_down, triad_inversion_2_up, triad_inversion_3_down, triad_inversion_3_up)
        );
        return chordMakerGroup;
    }

    private ChordMakerGroup buildTetradMakerGroup(ChromaticNotesList notePool) {
        ChordMaker tetrad = new ChordMaker(notePool, ChordPattern.TETRAD);
        ChordMaker tetrad_inverted = new ChordMaker(notePool, ChordPattern.TETRAD_INVERTED);
        ChordMaker tetrad_seventh = new ChordMaker(notePool, ChordPattern.TETRAD_SEVENTH);
        ChordMakerGroup chordMakerGroup = new ChordMakerGroup(List.of(tetrad, tetrad_inverted, tetrad_seventh));
        return chordMakerGroup;
    }


}
