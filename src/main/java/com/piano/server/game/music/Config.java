package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.WhichHands;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;

public class Config {


    private KeySigNote keySigNote;
    private KeySigMode keySigMode;
    private ChordPool chordPool;
    private WhichHands hands;
    private int leftMin;
    private int leftMax;
    private int rightMin;
    private int rightMax;
    private int length = 25;

    public Config(KeySigNote keySigNote, KeySigMode keySigMode, ChordPool chordPool, WhichHands hands, int leftMin, int leftMax, int rightMin, int rightMax, int length) {
        this.keySigNote = keySigNote;
        this.keySigMode = keySigMode;
        this.chordPool = chordPool;
        this.hands = hands;
        this.leftMin = leftMin;
        this.leftMax = leftMax;
        this.rightMin = rightMin;
        this.rightMax = rightMax;
        this.length = length;
    }

    public Config(KeySigNote note, KeySigMode mode, ChordPool chordPool, WhichHands hands, int length) {
        this.keySigNote = note;
        this.keySigMode = mode;
        this.chordPool = chordPool;
        this.hands = hands;
        this.length = length;
    }

    public Config(KeySigNote note, KeySigMode mode, ChordPool chordPool, WhichHands hands) {
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

    public int getLeftMin() {
        return leftMin;
    }

    public int getLeftMax() {
        return leftMax;
    }

    public int getRightMin() {
        return rightMin;
    }

    public int getRightMax() {
        return rightMax;
    }
}
