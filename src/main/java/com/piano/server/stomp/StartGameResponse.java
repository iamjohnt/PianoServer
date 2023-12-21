package com.piano.server.stomp;

public class StartGameResponse {

    private Boolean isStartSuccess;

    public StartGameResponse(Boolean isStartSuccess) {
        this.isStartSuccess = isStartSuccess;
    }

    public Boolean getStartSuccess() {
        return isStartSuccess;
    }

    public void setStartSuccess(Boolean startSuccess) {
        isStartSuccess = startSuccess;
    }
}
