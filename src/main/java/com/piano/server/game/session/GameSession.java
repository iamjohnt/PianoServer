package com.piano.server.game.session;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.Config;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;

import java.util.List;

public class GameSession {

    private MusicMakable musicMaker;
    private String session_id;
    private int session_user_id;
    private List<Chord> music;
    private int curChordPointer;

    public GameSession() {
        session_id = "1";
    }

    public GameSession(String session_id, Config config) {
        MusicMakerFactory factory = new MusicMakerFactory();
        this.musicMaker = factory.buildMusicMaker(config);
        this.session_id = session_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void startGame() {
        music = musicMaker.makeMusic();
    }

    public String toString() {
        return "Session ID: " + session_id + " : Music: " + music.toString();
    }
}
