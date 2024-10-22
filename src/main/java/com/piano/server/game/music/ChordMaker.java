package com.piano.server.game.music;

import java.util.Arrays;

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

        topNoteDistanceFromRoot = getTopNotePositionDistanceFromRoot(chordPattern);
        botNoteDistanceFromRoot = getBotNotePositionDistanceFromRoot(chordPattern);
    }

    private int getTopNotePositionDistanceFromRoot(int[] chordPattern) {
        int curMax = Integer.MIN_VALUE;
        for (int curNote : chordPattern) {
            if (curNote > curMax) {
                curMax = curNote;
            }
        }
        return curMax;
    }

    private int getBotNotePositionDistanceFromRoot(int[] chordPattern) {
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
            try {
                chordNotes[i] = notePool.getNoteFromInterval(root, offset);
            } catch (NoteOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return new Chord(chordNotes);
    }

    public int getTopNotePositionDistanceFromRoot() {
        return topNoteDistanceFromRoot;
    }

    public int getBotNotePositionDistanceFromRoot() {
        return botNoteDistanceFromRoot;
    }

    public int getChordNoteCount() {
        return chordNoteCount;
    }

    public ChromaticNotesList getNotePool() {
        return notePool;
    }

    @Override
    public String toString() {
        return Arrays.toString(chordPattern);
    }
}
