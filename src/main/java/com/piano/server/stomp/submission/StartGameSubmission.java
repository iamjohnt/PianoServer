package com.piano.server.stomp.submission;

public class StartGameSubmission {

    // value does not matter, it can be anything. only thing that matters is that this message is received, and the game is started
    private String start;

    public StartGameSubmission(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
