package com.piano.server.stomp;

public class EndGameResponse {

    private Boolean isGameEndSuccess;

    public EndGameResponse(Boolean isGameEndSuccess) {
        this.isGameEndSuccess = isGameEndSuccess;
    }

    public Boolean getGameEndSuccess() {
        return isGameEndSuccess;
    }

    public void setGameEndSuccess(Boolean gameEndSuccess) {
        isGameEndSuccess = gameEndSuccess;
    }
}
