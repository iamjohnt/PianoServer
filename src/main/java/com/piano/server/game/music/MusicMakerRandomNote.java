package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.Rand;
import com.piano.server.game.util.WhichHands;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MusicMakerRandomNote implements MusicMakable{

    private ChromaticNotesList notePool;
    private WhichHands whichHands;
    private KeySigNote note;
    private KeySigMode mode;
    private int lmin;
    private int lmax;
    private int rmin;
    private int rmax;
    private int length;
    private List<Integer> leftAndOrRightHand;


    public MusicMakerRandomNote(Config config) {
        extractConfigToVars(config);
        notePool = new ChromaticNotesList(note, mode);
    }

    @Override
    public Deque<Chord> makeMusic() {
        Deque<Chord> music = null;
        switch (whichHands) {
            case LEFT -> music = generateLeftHandMusic();
            case RIGHT -> music = generateRightHandMusic();
            case BOTH -> music = generateBothHandMusic();
        }
        return music;
    }

    private Deque<Chord> generateLeftHandMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            music.add(createRandomNote(lmin, lmax));
        }
        return music;
    }

    private Deque<Chord> generateRightHandMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            music.add(createRandomNote(rmin, rmax));
        }
        return music;
    }

    private Deque<Chord> generateBothHandMusic() {
        int LEFT = 0;
        int RIGHT = 1;
        int curHand = LEFT;

        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (curHand == LEFT) {
                music.add(createRandomNote(lmin, lmax));
                curHand = RIGHT;
            } else {
                music.add(createRandomNote(rmin, rmax));
                curHand = LEFT;
            }
        }
        return music;
    }

    private Chord createRandomNote(int min, int max) {
        int minNotePosition = notePool.getPositionByNote(min);
        int maxNotePosition = notePool.getPositionByNote(max);
        int randNotePosition = Rand.getRandInclusiveBetween(minNotePosition, maxNotePosition);
        int randNote = notePool.getNoteByPosition(randNotePosition);
        return new Chord(randNote);
    }

    private void extractConfigToVars(Config config) {
        this.whichHands = config.getHands();
        this.lmin = config.getLeftMin();
        this.lmax = config.getLeftMax();
        this.rmin = config.getRightMin();
        this.rmax = config.getRightMax();
        this.length = config.getLength();
        this.note = config.getKeySigNote();
        this.mode = config.getKeySigMode();
    }
}
