package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;

import java.util.Deque;

public class MusicMakerRandomTriad implements MusicMakable {

    private final int MIN_INTERVAL = 1;
    private final int MAX_INTERVAL = 7;
    private ChromaticNotesList notePool;
    private WhichHands whichHands;
    private KeySigNote note;
    private KeySigMode mode;
    private int lmin;
    private int lmax;
    private int rmin;
    private int rmax;
    private int length;

    public MusicMakerRandomTriad(Config config) {
    }

    @Override
    public Deque<Chord> makeMusic() {
        return null;
    }

    public Chord createRandomTriad(int min, int max) {
        // TODO implement

        /*
        i randomly choose the root, within the bounds
        i randomly choose the inversion
        i build the chord
        if it's out of bounds, i will make it within bounds
        */
        return null;
    }

    private void extractConfigToVars(Config config) {
        this.whichHands = config.getHands();
        this.lmin = config.getLeftMin();
        this.lmax = config.getLeftMax();
        this.rmin = config.getRightMin();
        this.rmax = config.getRightMax();
        this.length = config.getLength();
        this.note = config.getKeySigNote();
        this.mode = config.getKeySigMode();
    }

}
