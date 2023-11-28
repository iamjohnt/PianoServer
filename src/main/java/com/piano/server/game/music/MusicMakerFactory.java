package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;

// based on config, output appropriate music generator
public class MusicMakerFactory {

    private Config config;
    public MusicMakerFactory(Config config) {
        this.config = config;
    }

    public MusicMakable getMusicMaker() {
        MusicMakable rtn = null;
        ChordPool chordPool = config.getChordPool();

        switch (chordPool) {
            case TRIAD :
                rtn = new MusicMakerTwoCMajorTriads();
                break;
        }

        return rtn;
    }

}
