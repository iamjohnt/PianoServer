package com.piano.server.music;

import com.piano.server.game.music.Chord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ChordTest {

    @Test
    public void test_equals() {
        Chord one = new Chord(10, 50, 32);
        Chord two = new Chord(50, 32, 10);
        assert (one.equals(two));
    }

    @Test
    public void test_note_equals() {
        Chord one = new Chord(10, 50, 32);
        Chord two = new Chord(33, 12, 54);
        assert (!one.equals(two));
    }

    @Test
    public void test_to_string() {
        Chord chord = new Chord(21, 22);
        assertEquals(chord.toString(), "21 22 ");
    }


    @Test
    public void test_set_using_set() {
        Set<Integer> set = new HashSet<>();
        set.add(21);
        set.add(22);
        Chord actual = new Chord(set);
        Chord expected = new Chord(21,22);
        assertEquals(actual, expected);
    }

    @Test
    public void test_set_using_array_with_dupes() {
        Chord actual = new Chord(21, 22, 22);
        Chord expected = new Chord(21, 22);
        assertEquals(expected, actual);
    }

    @Test
    public void test_chord_list_contains_method_works() {
        List<Chord> chords = new ArrayList<>();
        chords.add(new Chord(21, 22, 23));
        chords.add(new Chord(100, 101, 102));

        Chord check = new Chord(23, 22, 21);

        assert (chords.contains(check));
    }
}
