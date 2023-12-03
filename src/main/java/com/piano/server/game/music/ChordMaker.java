package com.piano.server.game.music;

public class ChordMaker {

    private static final int OCT = 7;

    public static final int[] NOTE = {1};
    public static final int[] INTERVAL_SECOND = {1,2};
    public static final int[] INTERVAL_THIRD = {1,3};
    public static final int[] INTERVAL_FOURTH = {1,4};
    public static final int[] INTERVAL_FIFTH = {1,5};
    public static final int[] INTERVAL_SIXTH = {1,6};
    public static final int[] INTERVAL_SEVENTH = {1,7};
    public static final int[] INTERVAL_OCTAVE = {1,8};

    public static final int[] TRIAD = {1,3,5};
    public static final int[] TRIAD_SUS_2 = {1,2,5};
    public static final int[] TRIAD_SUS_4 = {1,4,5};
    public static final int[] TRIAD_INVERSION_2_UP = {3,5,1+OCT};
    public static final int[] TRIAD_INVERSION_2_DOWN = {3-OCT,5-OCT,1};
    public static final int[] TRIAD_INVERSION_3_UP = {5,1+OCT,3+OCT};
    public static final int[] TRIAD_INVERSION_3_DOWN = {5-OCT,1,3};

    public static final int[] TETRAD = {1,3,5,1+OCT};
    public static final int[] TETRAD_INVERTED = {5-OCT,3,5,1};
    public static final int[] TETRAD_SEVENTH = {1,3,5,7};

    /*
    rare:
    public static final int[] TRIAD_SUS_2_INVERSION_2_UP = {placeholder};
    public static final int[] TRIAD_SUS_2_INVERSION_2_DOWN = {placeholder};
    public static final int[] TRIAD_SUS_4_INVERSION_3_UP = {placeholder};
    public static final int[] TRIAD_SUS_4_INVERSION_3_DOWN = {placeholder};
    // tetrad inversions
    */

    private ChromaticNotesList notePool;
    private int[] chordPattern;

    public ChordMaker(ChromaticNotesList notePool, int[] ChordType) {
        this.notePool = notePool;
        this.chordPattern = ChordType;
    }

    public Chord createChord(int root) {
        int[] chordNotes = new int[chordPattern.length];

        for (int i = 0; i < chordNotes.length; i++) {
            int offset = chordPattern[i];
            chordNotes[i] = notePool.getNoteFromInterval(root, offset);
        }
        return new Chord(chordNotes);
    }

    public void setNotePool(ChromaticNotesList notePool) {
        this.notePool = notePool;
    }
}
