package com.piano.server.stomp.response;

public class EndSessionResponse {

    private boolean isEndSessionSuccess;
    private String sessionId;
    private String message;

    public EndSessionResponse(boolean isEndSessionSuccess, String message) {
        this.isEndSessionSuccess = isEndSessionSuccess;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isEndSessionSuccess() {
        return isEndSessionSuccess;
    }

    public void setEndSessionSuccess(boolean endSessionSuccess) {
        isEndSessionSuccess = endSessionSuccess;
    }
}
