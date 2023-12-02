package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ChromaticNotesList {

    private Logger log;
    private final int LAST_NOTE_ON_PIANO = 108;
    private final int FIRST_NOTE_ON_PIANO = 21;
    private List<Integer> chromaticNotes;
    private Map<Integer, Integer> notePositionMap;
    private KeySigNote keySigNote;
    private KeySigMode keySigMode;

    public ChromaticNotesList(KeySigNote note, KeySigMode mode) {
        this.log = LoggerFactory.getLogger(ChromaticNotesList.class);
        this.keySigNote = note;
        this.keySigMode = mode;
        this.chromaticNotes = calcChromaticNotes(this.keySigNote, this.keySigMode);
        this.notePositionMap = mapNotesToArrayPosition(this.chromaticNotes);
    }

    public int getPositionByNote(int note) {
        try {
            return notePositionMap.get(note);
        } catch (NullPointerException e) {
            log.error(Integer.toString(note) + " is not a note present in key signature " + keySigNote + " " + keySigMode + " : " + notePositionMap.toString());
            return -1;
        }
    }

    public int getNoteFromInterval(int baseNote, int interval) {
        // within a scale in music theory, each position is identified by a number, 1 - 7, not 0 - 6. Therefore we need ot subtract the interval by 1
        int distFromBaseNote = interval - 1;
        int position = getPositionByNote(baseNote);
        int intervalNotePosition =  position + distFromBaseNote;
        return getNoteByPosition(intervalNotePosition);
    }

    public int getPositionByNoteRoundedDown(int note) {
        if (notePositionMap.containsKey(note)) {
            return notePositionMap.get(note);
        } else {
            return notePositionMap.get(note - 1);
        }
    }

    public int getPositionByNoteRoundedUp(int note) {
        if (notePositionMap.containsKey(note)) {
            return notePositionMap.get(note);
        } else {
            return notePositionMap.get(note + 1);
        }
    }

    public int getNoteByPosition(int position) {
        return chromaticNotes.get(position);
    }

    public int size() {
        return chromaticNotes.size();
    }

    public boolean containsNote(int note) {
        return notePositionMap.containsKey(note);
    }

    private List<Integer> calcChromaticNotes(KeySigNote note, KeySigMode mode) {

        int[] nextIntervals = new ModePatterns().getModePattern(mode);
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
