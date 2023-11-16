package com.piano.server.out;

public class ChordResponse {


    public ChordResponse() {
    }

    public ChordResponse(String result_arg) {
        this.result = result_arg;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
