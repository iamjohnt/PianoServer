package com.piano.server.game.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
import com.piano.server.game.util.GameState;
import com.piano.server.stomp.response.ChordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class GameSession {

    private GameState state;
    private MusicMakable musicMaker;
    private String session_id;
    private int session_user_id;
    private Queue<Chord> music;
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
        music = musicMaker.makeMusic().stream().collect(Collectors.toCollection(LinkedList::new));
        curChordPointer = 0;
        log.info("game has started\n");
        log.info("chord lesson queue: " + music.toString());
        state.setCurrentState(GameState.State.STARTED);
    }

    public void endGame() {
        state.setCurrentState(GameState.State.FINISHING);
        music = null;
        curChordPointer = 0;
        log.info("user ended game");
        state.setCurrentState(GameState.State.UNSTARTED);
    }

    public ChordResponse checkChordAdvanceIfCorrect(Chord chordSubmission) {

        ChordResponse response = new ChordResponse();

        if (music == null) {
            log.info("game has not started yet");
            response.setIsChordProcessedSuccess(false)
                    .setSubmittedChord(chordSubmission)
                    .setActualChord(null)
                    .setIsSubmissionCorrect(null)
                    .setIsGameDone(null);
        }
        else if (music.isEmpty()) {
            log.info("lesson has already ended. following chord submission ignored: " + chordSubmission.toString());
            response.setIsChordProcessedSuccess(false)
                    .setSubmittedChord(chordSubmission)
                    .setActualChord(null)
                    .setIsSubmissionCorrect(null)
                    .setIsGameDone(null);
        }
        else if (chordSubmission.equals(music.peek())) {

            log.info("---- C O R R E C T:  " + chordSubmission.toString() + "\n");
            response.setIsChordProcessedSuccess(true)
                    .setSubmittedChord(chordSubmission)
                    .setActualChord(music.poll())
                    .setIsSubmissionCorrect(true);

            if (music.isEmpty()) {
                log.info("lesson has ended");
                this.endGame();
                response.setIsGameDone(true);
            } else {
                log.info("chord lesson queue: " + music.toString());
                response.setIsGameDone(false);
            }
        } else {
            log.info("------- W R O N G :  " + chordSubmission.toString() + "\n");
            log.info("chord lesson queue: " + music.toString());
            response.setIsChordProcessedSuccess(true)
                    .setSubmittedChord(chordSubmission)
                    .setActualChord(music.peek())
                    .setIsSubmissionCorrect(false)
                    .setIsGameDone(false);
        }
        return response;
    }

    public void setConfig(Config config) {
        MusicMakerFactory factory = new MusicMakerFactory();
        this.musicMaker = factory.buildMusicMaker(config);
    }

    public List<Chord> getMusic() {
        return music.stream().collect(Collectors.toCollection(LinkedList::new));
    }

    public String toString() {
        return "Session ID: " + session_id + ", Music: " + music.toString() + ", curChordPointer : " + curChordPointer;
    }
}
