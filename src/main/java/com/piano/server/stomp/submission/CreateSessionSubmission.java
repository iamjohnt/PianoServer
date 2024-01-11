package com.piano.server.stomp.submission;

public class CreateSessionSubmission {

    private String dummyMessage; // this can be anything. as long as the controller endpoint receives something, it will start the session

    public String getDummyMessage() {
        return dummyMessage;
    }

    public void setDummyMessage(String dummyMessage) {
        this.dummyMessage = dummyMessage;
    }
}
