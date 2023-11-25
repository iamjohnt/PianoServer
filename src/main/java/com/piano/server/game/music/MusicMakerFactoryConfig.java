package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.WhichHands;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;

public class MusicMakerFactoryConfig {


    private KeySigNote keySigNote;
    private KeySigMode keySigMode;
    private ChordPool chordPool;
    private WhichHands hands;
    private int length = 25;


    public MusicMakerFactoryConfig(KeySigNote note, KeySigMode mode, ChordPool chordPool, WhichHands hands, int length) {
        this.keySigNote = note;
        this.keySigMode = mode;
        this.chordPool = chordPool;
        this.hands = hands;
        this.length = length;
    }

    public MusicMakerFactoryConfig(KeySigNote note, KeySigMode mode, ChordPool chordPool, WhichHands hands) {
        this.keySigNote = note;
        this.keySigMode = mode;
        this.chordPool = chordPool;
        this.hands = hands;
    }

    public KeySigNote getKeySigNote() {
        return keySigNote;
    }

    public KeySigMode getKeySigMode() {
        return keySigMode;
    }

    public ChordPool getChordPool() {
        return chordPool;
    }

    public WhichHands getHands() {
        return hands;
    }

    public int getLength() {
        return length;
    }
}
