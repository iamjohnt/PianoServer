package com.piano.server.game.music;

import java.util.Deque;
import java.util.LinkedList;

public class MusicMakerTwoCMajorTriads implements MusicMakable {

    @Override
    public Deque<Chord> makeMusic() {
        Chord one = new Chord(60, 64, 67);
        Chord two = new Chord(60, 64, 67);
        Deque<Chord> rtn = new LinkedList<>();
        rtn.add(one);
        rtn.add(two);
        return rtn;
    }
}
