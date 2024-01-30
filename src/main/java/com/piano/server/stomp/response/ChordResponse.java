package com.piano.server.stomp.response;

import com.piano.server.game.music.Chord;

public class ChordResponse {


    public ChordResponse() {
    }

    private Boolean isChordProcessedSuccess;
    private Chord submittedChord;
    private Chord actualChord;
    private Boolean isSubmissionCorrect;
    private Boolean isGameDone;

    public Boolean getIsChordProcessedSuccess() {
        return isChordProcessedSuccess;
    }

    public Chord getSubmittedChord() {
        return submittedChord;
    }

    public Chord getActualChord() {
        return actualChord;
    }

    public Boolean isSubmissionCorrect() {
        return isSubmissionCorrect;
    }

    public Boolean getIsGameDone() {
        return isGameDone;
    }

    public ChordResponse setIsChordProcessedSuccess(Boolean chordProcessedSuccess) {
        isChordProcessedSuccess = chordProcessedSuccess;
        return this;
    }

    public ChordResponse setSubmittedChord(Chord submittedChord) {
        this.submittedChord = submittedChord;
        return this;
    }

    public ChordResponse setActualChord(Chord actualChord) {
        this.actualChord = actualChord;
        return this;
    }

    public ChordResponse setIsSubmissionCorrect(Boolean submissionCorrect) {
        isSubmissionCorrect = submissionCorrect;
        return this;
    }

    public ChordResponse setIsGameDone(Boolean actualChordFinalChord) {
        isGameDone = actualChordFinalChord;
        return this;
    }

    @Override
    public String toString() {
        return "ChordResponse{" +
                "submittedChord=" + submittedChord +
                ", correctChord=" + actualChord +
                ", isCorrect=" + isSubmissionCorrect +
                '}';
    }
}
