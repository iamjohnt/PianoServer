package com.piano.server.game.music;

public class SessionStart {

    private Config config;
    public SessionStart(Config config) {
        this.config = config;

        MusicMakerFactory musicMakerFactory = new MusicMakerFactory(config);
        MusicMakable musicMaker = musicMakerFactory.getMusicMaker();
    }

    public void createSession() {

    }

}
