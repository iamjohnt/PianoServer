package com.piano.server.stomp.response;

public class EndGameResponse {

    private Boolean isGameEndSuccess;
    private String message;

    public EndGameResponse(Boolean isGameEndSuccess, String message) {
        this.isGameEndSuccess = isGameEndSuccess;
        this.message = message;
    }

    public Boolean getGameEndSuccess() {
        return isGameEndSuccess;
    }

    public void setGameEndSuccess(Boolean gameEndSuccess) {
        isGameEndSuccess = gameEndSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
