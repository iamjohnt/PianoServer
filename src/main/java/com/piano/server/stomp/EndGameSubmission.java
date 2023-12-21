package com.piano.server.stomp;

public class EndGameSubmission {

    // value does not matter, it can be anything. only thing that matters is that this message is received, and the game is started
    private String end;

    public EndGameSubmission(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
