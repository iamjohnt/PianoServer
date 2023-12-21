package com.piano.server.stomp.response;

public class GameSettingsResponse {

    public boolean success;

    public GameSettingsResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
