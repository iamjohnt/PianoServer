package com.piano.server.stomp.submission;

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
    
    public GameSettingsSubmission() {
        
    }

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

    public GameSettingsSubmission setKeySigNote(KeySigNote keySigNote) {
        this.keySigNote = keySigNote;
        return this;
    }

    public KeySigMode getKeySigMode() {
        return keySigMode;
    }

    public GameSettingsSubmission setKeySigMode(KeySigMode keySigMode) {
        this.keySigMode = keySigMode;
        return this;
    }

    public ChordPool getChordPool() {
        return chordPool;
    }

    public GameSettingsSubmission setChordPool(ChordPool chordPool) {
        this.chordPool = chordPool;
        return this;
    }

    public WhichHands getWhichHands() {
        return whichHands;
    }

    public GameSettingsSubmission setWhichHands(WhichHands whichHands) {
        this.whichHands = whichHands;
        return this;
    }

    public int getLeftMin() {
        return leftMin;
    }

    public GameSettingsSubmission setLeftMin(int leftMin) {
        this.leftMin = leftMin;
        return this;
    }

    public int getLeftMax() {
        return leftMax;
    }

    public GameSettingsSubmission setLeftMax(int leftMax) {
        this.leftMax = leftMax;
        return this;
    }

    public int getRightMin() {
        return rightMin;
    }

    public GameSettingsSubmission setRightMin(int rightMin) {
        this.rightMin = rightMin;
        return this;
    }

    public int getRightMax() {
        return rightMax;
    }

    public GameSettingsSubmission setRightMax(int rightMax) {
        this.rightMax = rightMax;
        return this;
    }

    public int getLength() {
        return length;
    }

    public GameSettingsSubmission setLength(int length) {
        this.length = length;
        return this;
    }

    @Override
    public String toString() {
        return "GameSettingsSubmission{" +
                "keySigNote=" + keySigNote +
                ", keySigMode=" + keySigMode +
                ", chordPool=" + chordPool +
                ", whichHands=" + whichHands +
                ", leftMin=" + leftMin +
                ", leftMax=" + leftMax +
                ", rightMin=" + rightMin +
                ", rightMax=" + rightMax +
                ", length=" + length +
                '}';
    }
}
