package com.piano.server.game.music;

import java.util.HashSet;
import java.util.Set;

public class Chord {

    private Set<Integer> chord;

    public Chord(Set<Integer> chordIn) {
        this.chord = chordIn;
    }

    public Chord(int... notes) {
        this.chord = new HashSet<>();
        for (int note : notes) {
            chord.add(note);
        }
    }

    public Set<Integer> getChord() {
        return chord;
    }

    @Override
    public String toString() {
        String rtn = "";
        for (Integer note : chord) {
            rtn += note.toString() + " ";
        }
        return rtn;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Chord)) {
            return false;
        }
        Chord casted = (Chord) o;
        return this.chord.equals(casted.getChord());
    }


}
