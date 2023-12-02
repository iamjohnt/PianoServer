package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChordMakerTriad implements ChordMakable{

    private Logger log;
    private ChromaticNotesList notePool;
    private KeySigNote note;
    private KeySigMode mode;

    public ChordMakerTriad(KeySigNote note, KeySigMode mode) {
        this.log = LoggerFactory.getLogger(ChordMakerTriad.class);
        this.note = note;
        this.mode = mode;
        notePool = new ChromaticNotesList(note, mode);
    }

    @Override
    public Chord makeChord(int root) {
        if (notePool.containsNote(root)) {
            Chord rtn = new Chord();
            int note1position = notePool.getPositionByNote(root);
            int note2position = note1position + 2;
            int note3position = note1position + 4;

            int note1 = root;
            int note2 = notePool.getNoteByPosition(note2position);
            int note3 = notePool.getNoteByPosition(note3position);
            return new Chord(note1, note2, note3);
        } else {
            log.error(Integer.toString(root) + " is not a note present in key signature " + note + " " + mode);
            return null;
        }
    }
}
