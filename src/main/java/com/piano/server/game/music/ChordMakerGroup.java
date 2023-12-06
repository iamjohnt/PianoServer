package com.piano.server.game.music;

import java.util.ArrayList;
import java.util.List;

public class ChordMakerGroup {

    private List<ChordMaker> chordMakers;

    public ChordMakerGroup(List<ChordMaker> chordMakers) {
        this.chordMakers = chordMakers;
    }

    public ChordMakerGroup() {
        this.chordMakers = new ArrayList<>();
    }

    public ChordMakerGroup addChordMaker(ChordMaker chordMaker) {
        chordMakers.add(chordMaker);
        return this;
    }

    public ChordMaker getChordMaker(int index) {
        return chordMakers.get(index);
    }

    public List<ChordMaker> getChordMakers() {
        return chordMakers;
    }
}
