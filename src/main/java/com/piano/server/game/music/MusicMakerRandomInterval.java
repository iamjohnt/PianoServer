package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.Rand;
import com.piano.server.game.util.WhichHands;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MusicMakerRandomInterval implements MusicMakable {

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
    private List<Integer> leftAndOrRightHand;

    public MusicMakerRandomInterval(Config config) {
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
        return music;    }

    private Deque<Chord> generateLeftHandMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            music.add(createRandomInterval(lmin, lmax));
        }
        return music;
    }

    private Deque<Chord> generateRightHandMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            music.add(createRandomInterval(rmin, rmax));
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
                music.add(createRandomInterval(lmin, lmax));
                curHand = Rand.getRandInclusiveBetween(LEFT, RIGHT);
            } else {
                music.add(createRandomInterval(rmin, rmax));
                curHand = Rand.getRandInclusiveBetween(LEFT, RIGHT);
            }
        }
        return music;
    }

    public Chord createRandomInterval(int min, int max) {
        // set bounds
        int minRootPosition = notePool.getPositionByNoteRoundedUp(min);
        int maxRootPosition = notePool.getPositionByNoteRoundedDown(max) - 1; // interval root must be at least 1 below max, so the second note can be at the max
        int maxPosition = notePool.getPositionByNoteRoundedDown(max);

        // get random root, and second note
        int rootPosition = Rand.getRandInclusiveBetween(minRootPosition, maxRootPosition);
        int randInterval = Rand.getRandInclusiveBetween(MIN_INTERVAL, MAX_INTERVAL);
        int secondNotePosition = rootPosition + randInterval;

        // if second note over limit, offset everything down
        if (secondNotePosition > maxRootPosition) {
            int offset = secondNotePosition - maxRootPosition;
            secondNotePosition = maxPosition;
        }

        return new Chord(
            notePool.getNoteByPosition(rootPosition),
            notePool.getNoteByPosition(secondNotePosition)
        );
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
