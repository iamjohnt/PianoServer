package com.piano.server.game.music;

import com.piano.server.game.util.ChordPool;

// based on config, output appropriate music generator
public class MusicMakerFactory {

    private Config config;
    public MusicMakerFactory(Config config) {
        this.config = config;
    }

    public MusicMakable getMusicMaker() {
        ChordPool pool = config.getChordPool();
        MusicMakable musicMaker = null;

        switch (pool) {
            case NOTE -> musicMaker = new MusicMakerRandomNote(config);
            case INTERVAL -> musicMaker = new MusicMakerRandomInterval(config);
            case TRIAD -> musicMaker = new MusicMakerRandomTriad(config);
//            case TETRAD -> musicMaker = new MusicMakerLevel4(config);
//            case NOTE_INTERVAL -> musicMaker = new MusicMakerLevel5(config);
//            case NOTE_INTERVAL_TRIAD -> musicMaker = new MusicMakerLevel6(config);
//            case NOTE_INTERVAL_TRIAD_TETRAD -> musicMaker = new MusicMakerLevel7(config);
        }


        /*
        if level one,
            music maker = new MusicMaker(
        * */
        return musicMaker;
    }

}
