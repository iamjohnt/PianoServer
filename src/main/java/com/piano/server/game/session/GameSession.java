package com.piano.server.game.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
import com.piano.server.stomp.ChordResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GameSession {

    private MusicMakable musicMaker;
    private String session_id;
    private int session_user_id;
    private List<Chord> music;
    private int curChordPointer;
    private Logger log;

    public GameSession() {
        session_id = "1";
    }

    public GameSession(String session_id, Config config) {
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
        music = musicMaker.makeMusic();
        curChordPointer = 0;
        log.info("music list: " + music.toString());
        log.info("current chord: " + music.get(0).toString() + " current chord index: " + Integer.toString(curChordPointer) + "\n");
    }

    public ChordResponse checkChordAdvanceIfCorrect(Chord chordSubmission) {

        if (music == null) {
            log.info("game has not started yet");
            return new ChordResponse(chordSubmission, null, false);
        }

        if (curChordPointer >= music.size()) {
            log.info("reached end of music");
            return new ChordResponse(chordSubmission, null, false);
        }

        if (chordSubmission.equals(music.get(curChordPointer))) {
            ChordResponse rtn = new ChordResponse(chordSubmission, music.get(curChordPointer), true);
            curChordPointer++;
            return rtn;
//            log.info("correct :^) " + chordSubmission.toString());
//            log.info("curr music: " + music.toString());
//            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
        } else {
            return new ChordResponse(chordSubmission, music.get(curChordPointer), false);
//            log.info("IN-CORRECT! " + chordSubmission.toString());
//            log.info("curr music: " + music.toString());
//            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
        }
    }

    public String toString() {
        return "Session ID: " + session_id + ", Music: " + music.toString() + ", curChordPointer : " + curChordPointer;
    }
}
