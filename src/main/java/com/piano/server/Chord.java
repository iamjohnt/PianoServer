package com.piano.server;

public class Chord {

    private String name;

    public ChordSubmission() {
    }

    public ChordSubmission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}