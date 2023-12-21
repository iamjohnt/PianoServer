package com.piano.server.stomp.response;

public class EndSessionSubmission {

    private String message; // data could be anything. as long as server gets something at the EndSession controller endpoint, it will end the session

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
