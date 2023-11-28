package com.piano.server.stomp;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;

public class GameSettingsSubmission {

    private KeySigNote keySigNote;
    private KeySigMode keySigMode;
    private ChordPool chordPool;
    private WhichHands whichHands;
    private int leftMin;
    private int leftMax;
    private int rightMin;
    private int rightMax;
    private int length = 25;

    public GameSettingsSubmission(KeySigNote keySigNote, KeySigMode keySigMode, ChordPool chordPool, WhichHands whichHands, int leftMin, int leftMax, int rightMin, int rightMax, int length) {
        this.keySigNote = keySigNote;
        this.keySigMode = keySigMode;
        this.chordPool = chordPool;
        this.whichHands = whichHands;
        this.leftMin = leftMin;
        this.leftMax = leftMax;
        this.rightMin = rightMin;
        this.rightMax = rightMax;
        this.length = length;
    }

    public KeySigNote getKeySigNote() {
        return keySigNote;
    }

    public void setKeySigNote(KeySigNote keySigNote) {
        this.keySigNote = keySigNote;
    }

    public KeySigMode getKeySigMode() {
        return keySigMode;
    }

    public void setKeySigMode(KeySigMode keySigMode) {
        this.keySigMode = keySigMode;
    }

    public ChordPool getChordPool() {
        return chordPool;
    }

    public void setChordPool(ChordPool chordPool) {
        this.chordPool = chordPool;
    }

    public WhichHands getWhichHands() {
        return whichHands;
    }

    public void setWhichHands(WhichHands whichHands) {
        this.whichHands = whichHands;
    }

    public int getLeftMin() {
        return leftMin;
    }

    public void setLeftMin(int leftMin) {
        this.leftMin = leftMin;
    }

    public int getLeftMax() {
        return leftMax;
    }

    public void setLeftMax(int leftMax) {
        this.leftMax = leftMax;
    }

    public int getRightMin() {
        return rightMin;
    }

    public void setRightMin(int rightMin) {
        this.rightMin = rightMin;
    }

    public int getRightMax() {
        return rightMax;
    }

    public void setRightMax(int rightMax) {
        this.rightMax = rightMax;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "GameSettingsSubmission {" +
                "keySigNote=" + keySigNote +
                ", keySigMode=" + keySigMode +
                ", chordPool=" + chordPool +
                ", whichHands=" + whichHands +
                ", length=" + length +
                " }";
    }
}
