package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;
import com.piano.server.game.util.KeySigNote;
import com.piano.server.game.util.Rand;
import com.piano.server.game.util.WhichHands;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MusicMakerRandom implements MusicMakable {

    private final int LEFT = 0;
    private final int RIGHT = 1;
    private ChromaticNotesList notePool;
    private WhichHands whichHands;
    private KeySigNote note;
    private KeySigMode mode;
    private int lmin;
    private int lmax;
    private int rmin;
    private int rmax;
    private int length;
    private List<Integer> leftOrRightOrBothHands;
    private ChordMakerPool chordMakerPool;

    public MusicMakerRandom(Config config) {
        this.leftOrRightOrBothHands = new ArrayList<>();
        extractConfigToVars(config);
    }

    public MusicMakerRandom() {
        this.leftOrRightOrBothHands = new ArrayList<>();
    }

    @Override
    public Deque<Chord> makeMusic() {
        return createRandomMusic();
    }

    private Deque<Chord> createRandomMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int curHand = Rand.getRandomElement(leftOrRightOrBothHands);
            if (curHand == LEFT) {
                music.add(createRandomChord(lmin, lmax));
            } else {
                music.add(createRandomChord(rmin, rmax));
            }
        }
        return music;
    }

    private Chord createRandomChord(int min, int max) {

        // TODO can generate music...but sometimes interval = 62, 65, when max bound is 64. Off by one.

        ChordMakerGroup randChordMakerGroup = Rand.getRandomElement(chordMakerPool.getChordMakerGroups());
        ChordMaker randChordMaker = Rand.getRandomElement(randChordMakerGroup.getChordMakers());

        int adjustedMin = min + randChordMaker.getBotNoteDistanceFromRoot();
        int adjustedMax = max - randChordMaker.getTopNoteDistanceFromRoot();

        int adjustedMinPosition = notePool.getPositionByNoteRoundedUp(adjustedMin);
        int adjustedMaxPosition = notePool.getPositionByNoteRoundedDown(adjustedMax);

        int randRootPosition = Rand.getRandInclusiveBetween(adjustedMinPosition, adjustedMaxPosition);

        int randRoot = notePool.getNoteByPosition(randRootPosition);

        Chord chord = randChordMaker.createChord(randRoot);
        return chord;

    }

    public void setChordMakerPool(ChordMakerPool chordMakerPool) {
        this.chordMakerPool = chordMakerPool;
    }

    private void extractConfigToVars(Config config) {
        this.notePool = new ChromaticNotesList(
                config.getKeySigNote(),
                config.getKeySigMode()
        );
        if (config.getHands() == WhichHands.LEFT) {
            leftOrRightOrBothHands.add(LEFT);
        } else if (config.getHands() == WhichHands.RIGHT) {
            leftOrRightOrBothHands.add(RIGHT);
        } else {
            leftOrRightOrBothHands.add(LEFT, RIGHT);
        }

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
