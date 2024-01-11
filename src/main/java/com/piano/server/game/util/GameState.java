package com.piano.server.game.util;


public class GameState {

    public enum State {
        UNSTARTED,
        STARTED,
        FINISHED,
        STARTING,
        FINISHING
    }

    private State currentState;

    public GameState() {
        this.currentState = State.UNSTARTED;
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
