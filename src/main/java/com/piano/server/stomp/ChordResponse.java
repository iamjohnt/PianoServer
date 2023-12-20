package com.piano.server.stomp;

import com.piano.server.game.music.Chord;

public class ChordResponse {


    public ChordResponse() {
    }

    public ChordResponse(Chord submittedChord, Chord correctChord, Boolean isCorrect) {
        this.submittedChord = submittedChord;
        this.correctChord = correctChord;
        this.isCorrect = isCorrect;
    }

    private Chord submittedChord;
    private Chord correctChord;
    private Boolean isCorrect;

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Chord getSubmittedChord() {
        return submittedChord;
    }

    public void setSubmittedChord(Chord submittedChord) {
        this.submittedChord = submittedChord;
    }

    public Chord getCorrectChord() {
        return correctChord;
    }

    public void setCorrectChord(Chord correctChord) {
        this.correctChord = correctChord;
    }

    @Override
    public String toString() {
        return "ChordResponse{" +
                "submittedChord=" + submittedChord +
                ", correctChord=" + correctChord +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
