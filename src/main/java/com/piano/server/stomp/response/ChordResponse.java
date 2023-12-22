package com.piano.server.stomp.response;

import com.piano.server.game.music.Chord;

public class ChordResponse {


    public ChordResponse() {
    }

    private Boolean isChordProcessedSuccess;
    private Chord submittedChord;
    private Chord correctChord;
    private Boolean isCorrect;
    private Boolean musicSequenceString;

    public ChordResponse(Boolean isChordProcessedSuccess, Chord submittedChord, Chord correctChord, Boolean isCorrect) {
        this.isChordProcessedSuccess = isChordProcessedSuccess;
        this.submittedChord = submittedChord;
        this.correctChord = correctChord;
        this.isCorrect = isCorrect;
    }



    public Boolean getMusicSequenceString() {
        return musicSequenceString;
    }

    public void setMusicSequenceString(Boolean musicSequenceString) {
        this.musicSequenceString = musicSequenceString;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean correct) {
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

    public Boolean getIsCorrect() {
        return isCorrect;
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
