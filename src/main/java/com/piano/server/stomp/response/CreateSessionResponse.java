package com.piano.server.stomp.response;

public class CreateSessionResponse {

    private Boolean isCreateSessionSuccess;
    private String message;

    public CreateSessionResponse(Boolean isCreateSessionSuccess, String message) {
        this.isCreateSessionSuccess = isCreateSessionSuccess;
        this.message = message;
    }

    public Boolean getIsCreateSessionSuccess() {
        return isCreateSessionSuccess;
    }

    public void setIsCreateSessionSuccess(Boolean isCreateSessionSuccess) {
        this.isCreateSessionSuccess = isCreateSessionSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
