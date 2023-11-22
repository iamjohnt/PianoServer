package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;

// based on config, output appropriate music generator
public class MusicMakerFactory {

    private MusicMakerFactoryConfig musicMakerFactoryConfig;
    public MusicMakerFactory(MusicMakerFactoryConfig musicMakerFactoryConfig) {
        this.musicMakerFactoryConfig = musicMakerFactoryConfig;
    }

    public MusicMakable getMusicMaker() {
        MusicMakable rtn = null;
        ChordPool chordPool = musicMakerFactoryConfig.getChordPool();

        switch (chordPool) {
            case TRIAD :
                rtn = new MusicMakerTwoCMajorTriads();
                break;
        }

        return rtn;
    }

}
