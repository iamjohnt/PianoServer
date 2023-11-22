package com.piano.server.stomp;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Chord {

    @JsonProperty("chord")
    private List<Integer> chord;

    public Chord() {
    }

    public Chord(List<Integer> chord) {
        this.chord = chord;
    }

    public List<Integer> getChord() {
        return chord;
    }

    public void setChord(List<Integer> chord) {
        this.chord = chord;
    }

    @Override
    public String toString() {
        return chord.toString();
    }
}
