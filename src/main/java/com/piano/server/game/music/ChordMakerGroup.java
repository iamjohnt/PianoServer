package com.piano.server.game.music;

import java.util.List;

public class ChordMakerGroup {

    private List<ChordMaker> chordMakers;

    public ChordMakerGroup(List<ChordMaker> chordMakers) {
        this.chordMakers = chordMakers;
    }

    public ChordMakerGroup() {
        this.chordMakers = chordMakers;
    }

    public void addChordMaker(ChordMaker chordMaker) {
        chordMakers.add(chordMaker);
    }

    public ChordMaker getChordMaker(int index) {
        return chordMakers.get(index);
    }


}
