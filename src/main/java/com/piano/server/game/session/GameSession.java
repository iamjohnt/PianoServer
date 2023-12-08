package com.piano.server.game.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
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

    public boolean validateChord(Chord chordSubmission) {
        if (curChordPointer > music.size()) {
            log.debug("reached end of music");
            return false;
        }

        if (chordSubmission.equals(music.get(curChordPointer))) {
            curChordPointer++;
            log.info("correct :^) " + chordSubmission.toString());
            log.info("curr music: " + music.toString());
            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
            return true;
        } else {
            log.info("IN-CORRECT! " + chordSubmission.toString());
            log.info("curr music: " + music.toString());
            log.info("curr chord: " + music.get(curChordPointer) + " curr index: " + Integer.toString(curChordPointer) + "\n");
            return false;
        }
    }

    public String toString() {
        return "Session ID: " + session_id + " : Music: " + music.toString();
    }
}
