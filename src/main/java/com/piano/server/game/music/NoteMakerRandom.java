package com.piano.server.game.music;

import com.piano.server.game.util.Rand;

public class NoteMakerRandom implements ChordMakable {

    private ChromaticNotesList chromaticNotesList;
    private int minBound;
    private int maxBound;

    public NoteMakerRandom(ChromaticNotesList chromaticNotesList, int minBound, int maxBound) {
        this.chromaticNotesList = chromaticNotesList;
        this.minBound = minBound;
        this.maxBound = maxBound;
    }

    @Override
    public Chord createChord() {
        int minNotePosition = chromaticNotesList.getNotePosition(minBound);
        int maxNotePosition = chromaticNotesList.getNotePosition(maxBound);
        int randNotePosition = Rand.getRandInclusiveBetween(minNotePosition, maxNotePosition);
        int randNote = chromaticNotesList.get(randNotePosition);
        return new Chord(randNote);
    }
}
