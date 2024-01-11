package com.piano.server.stomp.response;

import com.piano.server.game.music.Chord;

import java.util.List;

public class StartGameResponse {

    private Boolean isStartGameSuccess;
    private String message;
    private List<Chord> chordSequence;

    public StartGameResponse(Boolean isStartGameSuccess, String message, List<Chord> chordSequence) {
        this.isStartGameSuccess = isStartGameSuccess;
        this.message = message;
        this.chordSequence = chordSequence;
    }

    public Boolean getStartGameSuccess() {
        return isStartGameSuccess;
    }

    public void setStartGameSuccess(Boolean startSuccess) {
        isStartGameSuccess = startSuccess;
    }

    public List<Chord> getChordSequence() {
        return chordSequence;
    }

    public void setChordSequence(List<Chord> chordSequence) {
        this.chordSequence = chordSequence;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
