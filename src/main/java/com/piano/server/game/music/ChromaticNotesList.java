package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;

import java.util.*;

public class ChromaticNotesList {

    private final int LAST_NOTE_ON_PIANO = 108;
    private final int FIRST_NOTE_ON_PIANO = 21;
    private List<Integer> chromaticNotes;
    private Map<Integer, Integer> notePositionMap;

    public ChromaticNotesList(KeySigNote note, KeySigMode mode) {
        this.chromaticNotes = calcChromaticNotes(note, mode);
        this.notePositionMap = mapNotesToArrayPosition(this.chromaticNotes);
    }

    public int getNotePosition(int note) {
        int pos = notePositionMap.get(note);
        return pos;
    }

    public int getNotePositionRoundedDown(int note) {
        if (notePositionMap.containsKey(note)) {
            return notePositionMap.get(note);
        } else {
            return notePositionMap.get(note - 1);
        }
    }

    public int getNotePositionRoundedUp(int note) {
        if (notePositionMap.containsKey(note)) {
            return notePositionMap.get(note);
        } else {
            return notePositionMap.get(note + 1);
        }
    }

    public int get(int position) {
        return chromaticNotes.get(position);
    }

    public int size() {
        return chromaticNotes.size();
    }

    private List<Integer> calcChromaticNotes(KeySigNote note, KeySigMode mode) {

        int[] nextIntervals = new KeySignaturePatterns().getModePattern(mode);
        int startingNote = getFirstNoteOnPiano(note);
        int curNote = startingNote;
        int curInterval = 0;
        Set<Integer> chromaticNotes = new HashSet<>();

        // while going up the piano, add chromatic notes
        while (curNote <= LAST_NOTE_ON_PIANO) {
            chromaticNotes.add(curNote);
            curNote += nextIntervals[curInterval];
            curInterval = (curInterval + 1) % 7;
        }

        // while going down the piano, add chromatic notes
        curNote = startingNote;
        curInterval = nextIntervals.length - 1;
        while (curNote >= FIRST_NOTE_ON_PIANO) {
            chromaticNotes.add(curNote);
            curNote -= nextIntervals[curInterval];
            curInterval = (curInterval - 1) % 7;
            if (curInterval < 0) {
                curInterval += 7;
            }
        }

        return chromaticNotes.stream().toList();
    }

    private Map<Integer, Integer> mapNotesToArrayPosition(List<Integer> listOfNotes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < chromaticNotes.size(); i++) {
            map.put(chromaticNotes.get(i), i);
        }
        return map;
    }

    private int getFirstNoteOnPiano(KeySigNote note) {
        int base = -1;
        switch (note) {
            case A -> base = 21;
            case A_SHARP -> base = 22;
            case B_FLAT -> base = 22;
            case B -> base = 23;
            case C -> base = 24;
            case C_SHARP -> base = 25;
            case D_FLAT -> base = 25;
            case D -> base = 26;
            case D_SHARP -> base = 27;
            case E_FLAT -> base = 27;
            case E -> base = 28;
            case F -> base = 29;
            case F_SHARP -> base = 30;
            case G_FLAT -> base = 30;
            case G -> base = 31;
            case G_SHARP -> base = 32;
            case A_FLAT -> base = 32;
        }
        return base;
    }

}
