package com.piano.server.music;

import com.piano.server.game.music.ChromaticNotesList;
import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.junit.jupiter.api.Test;

public class ChromaticNotesListTest {

    @Test
    public void test_generate_c_major_notes() {
        System.out.println("_____ test_generate_C_major_notes _____");

        ChromaticNotesList chromaticNotesList = new ChromaticNotesList(KeySigNote.C, KeySigMode.MAJOR);

        int[] expectedFirstTenNotes = {21, 23, 24, 26, 28, 29, 31, 33, 35, 36};

        boolean isCorrect = true;
        for (int i = 0; i < expectedFirstTenNotes.length; i++) {
            if (expectedFirstTenNotes[i] != chromaticNotesList.get(i)) {
                isCorrect = false;
            }
        }
        assert (isCorrect);
    }
}
