package com.piano.server.stomp.response;

public class StartGameResponse {

    private Boolean isStartSuccess;
    private String message;

    public StartGameResponse(Boolean isStartSuccess, String message) {
        this.isStartSuccess = isStartSuccess;
        this.message = message;
    }

    public Boolean getStartSuccess() {
        return isStartSuccess;
    }

    public void setStartSuccess(Boolean startSuccess) {
        isStartSuccess = startSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
