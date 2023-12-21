package com.piano.server.game.util;


public class GameState {

    public enum State {
        UNSTARTED,
        STARTED,
        FINISHED
    }

    private State currentState;

    public GameState() {

    }

    public GameState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }
}
