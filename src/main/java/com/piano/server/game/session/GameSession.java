package com.piano.server.game.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
import com.piano.server.game.util.GameState;
import com.piano.server.stomp.response.ChordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GameSession {

    private GameState state;
    private MusicMakable musicMaker;
    private String session_id;
    private int session_user_id;
    private List<Chord> music;
    private int curChordPointer;
    private Logger log;

    public GameSession(String session_id) {
        this.state = new GameState(GameState.State.UNSTARTED);
        this.session_id = session_id;
        log = LoggerFactory.getLogger(GameSession.class);
    }

    public GameSession(String session_id, Config config) {
        state = new GameState(GameState.State.UNSTARTED);
        MusicMakerFactory factory = new MusicMakerFactory();
        this.musicMaker = factory.buildMusicMaker(config);
        this.session_id = session_id;
        log = LoggerFactory.getLogger(GameSession.class);
        log.info("GameSession config: " + config.toString());
    }

    public String getSession_id() {
        return session_id;
    }

    public void startGame() {
        state.setCurrentState(GameState.State.STARTING);
        music = musicMaker.makeMusic();
        curChordPointer = 0;
        log.info("music list: " + music.toString());
        log.info("current chord: " + music.get(0).toString() + " current chord index: " + Integer.toString(curChordPointer) + "\n");
        state.setCurrentState(GameState.State.STARTED);
    }

    public void endGame() {
        state.setCurrentState(GameState.State.FINISHING);
        music = null;
        curChordPointer = 0;
        log.info("user ended game");
        state.setCurrentState(GameState.State.FINISHED);
    }

    public ChordResponse checkChordAdvanceIfCorrect(Chord chordSubmission) {

        if (music == null) {
            log.info("game has not started yet");
            return new ChordResponse(false, chordSubmission, null, null);
        }

        if (curChordPointer >= music.size()) {
            log.info("reached end of music");
            return new ChordResponse(false, chordSubmission, null, null);
        }

        if (chordSubmission.equals(music.get(curChordPointer))) {
            ChordResponse rtn = new ChordResponse(true, chordSubmission, music.get(curChordPointer), true);
            curChordPointer++;
            return rtn;
//            log.info("correct :^) " + chordSubmission.toString());
//            log.info("curr music: " + music.toString());
//            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
        } else {
            return new ChordResponse(true, chordSubmission, music.get(curChordPointer), false);
//            log.info("IN-CORRECT! " + chordSubmission.toString());
//            log.info("curr music: " + music.toString());
//            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
        }
    }

    public void setConfig(Config config) {
        MusicMakerFactory factory = new MusicMakerFactory();
        this.musicMaker = factory.buildMusicMaker(config);
    }


    public List<Chord> getMusic() {
        return music;
    }

    public String toString() {
        return "Session ID: " + session_id + ", Music: " + music.toString() + ", curChordPointer : " + curChordPointer;
    }
}
