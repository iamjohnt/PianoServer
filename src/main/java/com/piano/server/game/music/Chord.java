package com.piano.server.game.music;

import java.util.HashSet;
import java.util.List;
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
        return this.chord;
    }

    public boolean isInBounds(int min, int max) {
        List<Integer> notes = this.chord.stream().sorted().toList();
        int bot = notes.get(0);
        int top = notes.get(notes.size() - 1);
        return
            bot >= min &&
            bot <= max &&
            top >= min &&
            top <= max;
    }

    @Override
    public String toString() {
        return this.chord.toString();
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
