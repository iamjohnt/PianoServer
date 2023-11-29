package com.piano.server.music;

import com.piano.server.game.music.Chord;
import com.piano.server.game.music.MusicMakable;
import com.piano.server.game.music.MusicMakerFactory;
import com.piano.server.game.music.Config;
import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;
import org.junit.jupiter.api.Test;

import java.util.Deque;

public class MusicMakerFactoryTest {

    @Test
    void from_config_to_music_sequence() {

        Deque<Chord> musicSeq = null;

        System.out.println("======================================== testMusicMaker ========================================");
        System.out.println("description: config -> session -> factory -> maker -> final music");

        // create music maker config
        Config config = new Config(
                KeySigNote.C,
                KeySigMode.MAJOR,
                ChordPool.NOTE,
                WhichHands.BOTH
        );

        // based on config, create appropriate music maker
        MusicMakerFactory factory = new MusicMakerFactory(config);
        MusicMakable maker = factory.getMusicMaker();

        // make the music
        musicSeq = maker.makeMusic();

        //assert
        System.out.println(musicSeq.toString());

    }



}
