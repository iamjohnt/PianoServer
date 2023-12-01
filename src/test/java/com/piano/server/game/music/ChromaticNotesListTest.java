package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChromaticNotesListTest {

    @Test
    public void test_get_note_position_rounded_down() {
        ChromaticNotesList chromaticNotesList = new ChromaticNotesList(KeySigNote.A_SHARP, KeySigMode.MINOR);
        int input = 33;
        int expected = 6;
        int actual = chromaticNotesList.getPositionByNoteRoundedDown(input);
        assertEquals(expected, actual);
    }

    @Test
    public void test_get_note_position_rounded_up() {
        ChromaticNotesList chromaticNotesList = new ChromaticNotesList(KeySigNote.B_FLAT, KeySigMode.MAJOR);
        int input = 23;
        int expected = 1;
        int actual = chromaticNotesList.getPositionByNoteRoundedDown(input);
        assertEquals(expected, actual);
    }

    @Test
    public void test_generate_c_major_notes() {
        System.out.println("_____ test_generate_C_major_notes _____");

        ChromaticNotesList actual = new ChromaticNotesList(KeySigNote.C, KeySigMode.MAJOR);

        List<Chord> expected = new ArrayList<>();
        expected.add(new Chord(21));
        expected.add(new Chord(23));
        expected.add(new Chord(24));
        expected.add(new Chord(26));
        expected.add(new Chord(28));
        expected.add(new Chord(29));
        expected.add(new Chord(31));
        expected.add(new Chord(33));
        expected.add(new Chord(35));
        expected.add(new Chord(36));
        expected.add(new Chord(38));
        expected.add(new Chord(40));
        expected.add(new Chord(41));
        expected.add(new Chord(43));
        expected.add(new Chord(45));
        expected.add(new Chord(47));
        expected.add(new Chord(48));
        expected.add(new Chord(50));
        expected.add(new Chord(52));
        expected.add(new Chord(53));
        expected.add(new Chord(55));
        expected.add(new Chord(57));
        expected.add(new Chord(59));
        expected.add(new Chord(60));
        expected.add(new Chord(62));
        expected.add(new Chord(64));
        expected.add(new Chord(65));
        expected.add(new Chord(67));
        expected.add(new Chord(69));
        expected.add(new Chord(71));
        expected.add(new Chord(72));
        expected.add(new Chord(74));
        expected.add(new Chord(76));
        expected.add(new Chord(77));
        expected.add(new Chord(79));
        expected.add(new Chord(81));
        expected.add(new Chord(83));
        expected.add(new Chord(84));
        expected.add(new Chord(86));
        expected.add(new Chord(88));
        expected.add(new Chord(89));
        expected.add(new Chord(91));
        expected.add(new Chord(93));
        expected.add(new Chord(95));
        expected.add(new Chord(96));
        expected.add(new Chord(98));
        expected.add(new Chord(100));
        expected.add(new Chord(101));
        expected.add(new Chord(103));
        expected.add(new Chord(105));
        expected.add(new Chord(107));
        expected.add(new Chord(108));

        boolean isCorrect = true;
        for (int i = 0; i < actual.size(); i++) {
            if (expected.get(i).equals(actual.getNoteByPosition(i))) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }

    @Test
    public void test_generate_c_minor_notes() {
        System.out.println("_____ test_generate_c_minor_notes _____");

        ChromaticNotesList actual = new ChromaticNotesList(KeySigNote.C, KeySigMode.MINOR);

        List<Chord> expected = new ArrayList<>();

        expected.add(new Chord(22));
        expected.add(new Chord(24));
        expected.add(new Chord(26));
        expected.add(new Chord(27));
        expected.add(new Chord(29));
        expected.add(new Chord(31));
        expected.add(new Chord(32));
        expected.add(new Chord(34));
        expected.add(new Chord(36));
        expected.add(new Chord(38));
        expected.add(new Chord(39));
        expected.add(new Chord(41));
        expected.add(new Chord(43));
        expected.add(new Chord(44));
        expected.add(new Chord(46));
        expected.add(new Chord(48));
        expected.add(new Chord(50));
        expected.add(new Chord(51));
        expected.add(new Chord(53));
        expected.add(new Chord(55));
        expected.add(new Chord(56));
        expected.add(new Chord(58));
        expected.add(new Chord(60));
        expected.add(new Chord(62));
        expected.add(new Chord(63));
        expected.add(new Chord(65));
        expected.add(new Chord(67));
        expected.add(new Chord(68));
        expected.add(new Chord(70));
        expected.add(new Chord(72));
        expected.add(new Chord(74));
        expected.add(new Chord(75));
        expected.add(new Chord(77));
        expected.add(new Chord(79));
        expected.add(new Chord(80));
        expected.add(new Chord(82));
        expected.add(new Chord(84));
        expected.add(new Chord(86));
        expected.add(new Chord(87));
        expected.add(new Chord(89));
        expected.add(new Chord(91));
        expected.add(new Chord(92));
        expected.add(new Chord(94));
        expected.add(new Chord(96));
        expected.add(new Chord(98));
        expected.add(new Chord(99));
        expected.add(new Chord(101));
        expected.add(new Chord(103));
        expected.add(new Chord(104));
        expected.add(new Chord(106));
        expected.add(new Chord(108));

        boolean isCorrect = true;
        for (int i = 0; i < actual.size(); i++) {
            if (expected.get(i).equals(actual.getNoteByPosition(i))) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }

    @Test
    public void test_generate_a_major_notes() {
        System.out.println("_____ test_generate_a_major_notes _____");

        ChromaticNotesList actual = new ChromaticNotesList(KeySigNote.A, KeySigMode.MAJOR);

        List<Chord> expected = new ArrayList<>();

        expected.add(new Chord(21));
        expected.add(new Chord(23));
        expected.add(new Chord(25));
        expected.add(new Chord(26));
        expected.add(new Chord(28));
        expected.add(new Chord(30));
        expected.add(new Chord(32));
        expected.add(new Chord(33));
        expected.add(new Chord(35));
        expected.add(new Chord(37));
        expected.add(new Chord(38));
        expected.add(new Chord(40));
        expected.add(new Chord(42));
        expected.add(new Chord(44));
        expected.add(new Chord(45));
        expected.add(new Chord(47));
        expected.add(new Chord(49));
        expected.add(new Chord(50));
        expected.add(new Chord(52));
        expected.add(new Chord(54));
        expected.add(new Chord(56));
        expected.add(new Chord(57));
        expected.add(new Chord(59));
        expected.add(new Chord(61));
        expected.add(new Chord(62));
        expected.add(new Chord(64));
        expected.add(new Chord(66));
        expected.add(new Chord(68));
        expected.add(new Chord(69));
        expected.add(new Chord(71));
        expected.add(new Chord(73));
        expected.add(new Chord(74));
        expected.add(new Chord(76));
        expected.add(new Chord(78));
        expected.add(new Chord(80));
        expected.add(new Chord(81));
        expected.add(new Chord(83));
        expected.add(new Chord(85));
        expected.add(new Chord(86));
        expected.add(new Chord(88));
        expected.add(new Chord(90));
        expected.add(new Chord(92));
        expected.add(new Chord(93));
        expected.add(new Chord(95));
        expected.add(new Chord(97));
        expected.add(new Chord(98));
        expected.add(new Chord(100));
        expected.add(new Chord(102));
        expected.add(new Chord(104));
        expected.add(new Chord(105));
        expected.add(new Chord(107));

        boolean isCorrect = true;
        for (int i = 0; i < actual.size(); i++) {
            if (expected.get(i).equals(actual.getNoteByPosition(i))) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }

    @Test
    public void test_generate_a_minor_notes() {
        System.out.println("_____ test_generate_a_minor_notes _____");

        ChromaticNotesList actual = new ChromaticNotesList(KeySigNote.A, KeySigMode.MINOR);

        List<Chord> expected = new ArrayList<>();

        expected.add(new Chord(21));
        expected.add(new Chord(23));
        expected.add(new Chord(24));
        expected.add(new Chord(26));
        expected.add(new Chord(28));
        expected.add(new Chord(29));
        expected.add(new Chord(31));
        expected.add(new Chord(33));
        expected.add(new Chord(35));
        expected.add(new Chord(36));
        expected.add(new Chord(38));
        expected.add(new Chord(40));
        expected.add(new Chord(41));
        expected.add(new Chord(43));
        expected.add(new Chord(45));
        expected.add(new Chord(47));
        expected.add(new Chord(48));
        expected.add(new Chord(50));
        expected.add(new Chord(52));
        expected.add(new Chord(53));
        expected.add(new Chord(55));
        expected.add(new Chord(57));
        expected.add(new Chord(59));
        expected.add(new Chord(60));
        expected.add(new Chord(62));
        expected.add(new Chord(64));
        expected.add(new Chord(65));
        expected.add(new Chord(67));
        expected.add(new Chord(69));
        expected.add(new Chord(71));
        expected.add(new Chord(72));
        expected.add(new Chord(74));
        expected.add(new Chord(76));
        expected.add(new Chord(77));
        expected.add(new Chord(79));
        expected.add(new Chord(81));
        expected.add(new Chord(83));
        expected.add(new Chord(84));
        expected.add(new Chord(86));
        expected.add(new Chord(88));
        expected.add(new Chord(89));
        expected.add(new Chord(91));
        expected.add(new Chord(93));
        expected.add(new Chord(95));
        expected.add(new Chord(96));
        expected.add(new Chord(98));
        expected.add(new Chord(100));
        expected.add(new Chord(101));
        expected.add(new Chord(103));
        expected.add(new Chord(105));
        expected.add(new Chord(107));
        expected.add(new Chord(108));
        boolean isCorrect = true;
        for (int i = 0; i < actual.size(); i++) {
            if (expected.get(i).equals(actual.getNoteByPosition(i))) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }

    @Test
    public void test_generate_e_flat_major_notes() {
        System.out.println("_____ test_generate_e_flat_major_notes _____");

        ChromaticNotesList actual = new ChromaticNotesList(KeySigNote.E_FLAT, KeySigMode.MAJOR);

        List<Chord> expected = new ArrayList<>();

        expected.add(new Chord(22));
        expected.add(new Chord(24));
        expected.add(new Chord(26));
        expected.add(new Chord(27));
        expected.add(new Chord(29));
        expected.add(new Chord(31));
        expected.add(new Chord(32));
        expected.add(new Chord(34));
        expected.add(new Chord(36));
        expected.add(new Chord(38));
        expected.add(new Chord(39));
        expected.add(new Chord(41));
        expected.add(new Chord(43));
        expected.add(new Chord(44));
        expected.add(new Chord(46));
        expected.add(new Chord(48));
        expected.add(new Chord(50));
        expected.add(new Chord(51));
        expected.add(new Chord(53));
        expected.add(new Chord(55));
        expected.add(new Chord(56));
        expected.add(new Chord(58));
        expected.add(new Chord(60));
        expected.add(new Chord(62));
        expected.add(new Chord(63));
        expected.add(new Chord(65));
        expected.add(new Chord(67));
        expected.add(new Chord(68));
        expected.add(new Chord(70));
        expected.add(new Chord(72));
        expected.add(new Chord(74));
        expected.add(new Chord(75));
        expected.add(new Chord(77));
        expected.add(new Chord(79));
        expected.add(new Chord(80));
        expected.add(new Chord(82));
        expected.add(new Chord(84));
        expected.add(new Chord(86));
        expected.add(new Chord(87));
        expected.add(new Chord(89));
        expected.add(new Chord(91));
        expected.add(new Chord(92));
        expected.add(new Chord(94));
        expected.add(new Chord(96));
        expected.add(new Chord(98));
        expected.add(new Chord(99));
        expected.add(new Chord(101));
        expected.add(new Chord(103));
        expected.add(new Chord(104));
        expected.add(new Chord(106));
        expected.add(new Chord(108));

        boolean isCorrect = true;
        for (int i = 0; i < actual.size(); i++) {
            if (expected.get(i).equals(actual.getNoteByPosition(i))) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }
}
