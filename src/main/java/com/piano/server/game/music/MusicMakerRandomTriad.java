package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.Rand;
import com.piano.server.game.util.WhichHands;

import java.util.Deque;
import java.util.LinkedList;

public class MusicMakerRandomTriad implements MusicMakable {

    private final int MIN_INTERVAL = 1;
    private final int MAX_INTERVAL = 7;
    private ChromaticNotesList notePool;
    private WhichHands whichHands;
    private KeySigNote note;
    private KeySigMode mode;
    private int lmin;
    private int lmax;
    private int rmin;
    private int rmax;
    private int length;

    public MusicMakerRandomTriad(Config config) {
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
            music.add(createRandomTriad(lmin, lmax));
        }
        return music;
    }

    private Deque<Chord> generateRightHandMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            music.add(createRandomTriad(rmin, rmax));
        }
        return music;
    }

    private Deque<Chord> generateBothHandMusic() {
        int LEFT = 0;
        int RIGHT = 1;
        int curHand = Rand.getRandInclusiveBetween(LEFT, RIGHT);

        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            if (curHand == LEFT) {
                music.add(createRandomTriad(lmin, lmax));
                curHand = Rand.getRandInclusiveBetween(LEFT, RIGHT);
            } else {
                music.add(createRandomTriad(rmin, rmax));
                curHand = Rand.getRandInclusiveBetween(LEFT, RIGHT);
            }
        }
        return music;
    }


    private Chord createRandomTriad(int min, int max) {
        int minPosition = notePool.getPositionByNoteRoundedUp(min);
        int maxPosition = notePool.getPositionByNoteRoundedDown(max) - 4; // top of triad is 4 places from root. top needs to be within bounds

        int randRootPosition = Rand.getRandInclusiveBetween(minPosition, maxPosition);
        int root = notePool.getNoteByPosition(randRootPosition);
        int note2 = notePool.getNoteFromInterval(root, 3);
        int note3 = notePool.getNoteFromInterval(root, 5);
        return new Chord(root, note2, note3);
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
