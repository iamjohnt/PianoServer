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
import java.util.HashSet;
import java.util.Set;

public class MusicMakerFactoryTest {

    @Test
    void test_create_music_maker_within_bounds() {

        Deque<Chord> musicSeq = null;

        // create music maker config
        Config config = new Config(
                KeySigNote.C,
                KeySigMode.MAJOR,
                ChordPool.NOTE,
                WhichHands.BOTH,
                36,
                38,
                60,
                64,
                100
        );

        // based on config, create appropriate music maker
        MusicMakerFactory factory = new MusicMakerFactory(config);
        MusicMakable maker = factory.getMusicMaker();

        // make the music
        musicSeq = maker.makeMusic();

        //assert
        Set<Integer> correctPoolOfNotes = new HashSet();
        correctPoolOfNotes.add(36);
        correctPoolOfNotes.add(38);
        correctPoolOfNotes.add(60);
        correctPoolOfNotes.add(62);
        correctPoolOfNotes.add(64);

        boolean isNotesWithinBounds = true;
        for (Chord note : musicSeq) {
            int cur = note.getChord().stream().toList().get(0);
            if (!correctPoolOfNotes.contains(cur)) {
                isNotesWithinBounds = false;
            }
        }
        assert (isNotesWithinBounds);
    }



}
