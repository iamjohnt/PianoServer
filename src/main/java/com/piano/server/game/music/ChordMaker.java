package com.piano.server.game.music;

public class ChordMaker {

    private ChromaticNotesList notePool;
    private int[] chordPattern;
    private int chordNoteCount;
    private int topNoteDistanceFromRoot;
    private int botNoteDistanceFromRoot;

    public ChordMaker(ChromaticNotesList notePool, int[] chordPattern) {
        this.notePool = notePool;
        this.chordPattern = chordPattern;
        this.chordNoteCount = chordPattern.length;

        topNoteDistanceFromRoot = getTopNoteDistanceFromRoot(chordPattern);
        botNoteDistanceFromRoot = getBotNoteDistanceFromRoot(chordPattern);
    }

    private int getTopNoteDistanceFromRoot(int[] chordPattern) {
        int curMax = Integer.MIN_VALUE;
        for (int curNote : chordPattern) {
            if (curNote > curMax) {
                curMax = curNote;
            }
        }
        return curMax;
    }

    private int getBotNoteDistanceFromRoot(int[] chordPattern) {
        int curMin = Integer.MAX_VALUE;
        for (int curNote : chordPattern) {
            if (curNote < curMin) {
                curMin = curNote;
            }
        }
        return curMin;
    }


    public Chord createChord(int root) {
        int[] chordNotes = new int[chordPattern.length];

        for (int i = 0; i < chordNotes.length; i++) {
            int offset = chordPattern[i];
            chordNotes[i] = notePool.getNoteFromInterval(root, offset);
        }
        return new Chord(chordNotes);
    }

    public int getTopNoteDistanceFromRoot() {
        return topNoteDistanceFromRoot;
    }

    public int getBotNoteDistanceFromRoot() {
        return botNoteDistanceFromRoot;
    }

    public int getChordNoteCount() {
        return chordNoteCount;
    }
}
