package com.piano.server.game.music;

import java.util.List;

public class ChordMakerPool {

    private List<ChordMakerGroup> chordMakerGroups;

    public ChordMakerPool(List<ChordMakerGroup> chordMakerGroups) {
        this.chordMakerGroups = chordMakerGroups;
    }

    public ChordMakerPool() {
    }

    public void addChordMakerGroup(ChordMakerGroup group) {
        this.chordMakerGroups.add(group);
    }

    public ChordMakerGroup getChordMakerGroup(int index) {
        return this.chordMakerGroups.get(index);
    }

    public List<ChordMakerGroup> getChordMakerGroups() {
        return chordMakerGroups;
    }
}
