package com.piano.server.stomp;

public class Response {

    private String content;

    public Response() {
    }

    public Response(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}