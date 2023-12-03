package com.piano.server.game.music;

import java.util.List;

public class ChordMakerPool {

    private List<ChordMakerGroup> groups;

    public ChordMakerPool(List<ChordMakerGroup> groups) {
        this.groups = groups;
    }

    public ChordMakerPool() {
    }

    public void addChordMakerGroup(ChordMakerGroup group) {
        this.groups.add(group);
    }

    public ChordMakerGroup getChordMakerGroup(int index) {
        return this.groups.get(index);
    }
}
