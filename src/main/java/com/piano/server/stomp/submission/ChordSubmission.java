package com.piano.server.stomp.submission;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

public class ChordSubmission {

    @JsonProperty("chord")
    private Set<Integer> chord;

    public ChordSubmission() {
    }

    public ChordSubmission(Set<Integer> chord) {
        this.chord = chord;
    }

    public Set<Integer> getChord() {
        return chord;
    }

    public void setChord(Set<Integer> chord) {
        this.chord = chord;
    }

    @Override
    public String toString() {
        return chord.toString();
    }
}
