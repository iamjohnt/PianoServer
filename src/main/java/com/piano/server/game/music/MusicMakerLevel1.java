package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.WhichHands;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MusicMakerLevel1 implements MusicMakable{

    private KeySigNote note;
    private KeySigMode mode;
    private ChordMakable leftHand;
    private ChordMakable rightHand;
    private List<ChordMakable> hands;
    private WhichHands whichHands;
    private int length;
    private int lMin;
    private int lMax;
    private int rMin;
    private int rMax;



    public MusicMakerLevel1(Config config) {

        extractConfigToMemberVariables(config);

        ChromaticNotesList modeNotes = new ChromaticNotesList(note, mode);

        hands = new ArrayList<>();

        switch (whichHands) {
            case LEFT -> {
                hands.add(new NoteMakerRandom(modeNotes, lMin, lMax));
            }
            case RIGHT -> {
                hands.add(new NoteMakerRandom(modeNotes, rMin, rMax));
            }
            case BOTH -> {
                hands.add(new NoteMakerRandom(modeNotes, lMin, lMax));
                hands.add(new NoteMakerRandom(modeNotes, rMin, rMax));
            }
        }
    }

    private void extractConfigToMemberVariables(Config config) {
        this.whichHands = config.getHands();
        this.length = config.getLength();
        this.note = config.getKeySigNote();
        this.mode = config.getKeySigMode();
        this.lMin = config.getLeftMin();
        this.lMax = config.getLeftMax();
        this.rMin = config.getRightMin();
        this.rMax = config.getRightMax();
    }

    @Override
    public Deque<Chord> makeMusic() {

        Deque<Chord> rtn = new LinkedList<>();
        int curHand = 0;
        for (int i = 0; i < length; i++) {
            Chord chord = hands.get(curHand).createChord();
            rtn.add(chord);
            curHand = (curHand + 1) % hands.size();
        }
        return rtn;
    }
}
