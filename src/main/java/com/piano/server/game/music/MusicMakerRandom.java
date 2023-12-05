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
    private WhichHands whichHands;
    private KeySigNote note;
    private KeySigMode mode;
    private int lmin;
    private int lmax;
    private int rmin;
    private int rmax;
    private int length;
    private List<Integer> handPool;
    private ChordMakerPool chordMakerPool;

    public MusicMakerRandom(Config config) {
        this.handPool = new ArrayList<>();
    }

    public MusicMakerRandom() {
        this.handPool = new ArrayList<>();
    }

    @Override
    public Deque<Chord> makeMusic() {
        return createRandomMusic();
    }

    private Deque<Chord> createRandomMusic() {
        Deque<Chord> music = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            int curHand = Rand.getRandomElement(handPool);
            if (curHand == LEFT) {
                music.add(createRandomChord(lmin, lmax));
            } else {
                music.add(createRandomChord(rmin, rmax));
            }
        }
        return music;
    }

    private Chord createRandomChord(int min, int max) {

        ChordMakerGroup randChordMakerGroup = Rand.getRandomElement(chordMakerPool.getChordMakerGroups());
        ChordMaker randChordMaker = Rand.getRandomElement(randChordMakerGroup.getChordMakers());

        ChromaticNotesList chromaticNotePool = randChordMaker.getNotePool();

        // adjust the min and max positions, so i can fit the chord properly
        int minPosition = chromaticNotePool.getPositionByNoteRoundedUp(min);
        int maxPosition = chromaticNotePool.getPositionByNoteRoundedDown(max);

        int adjustedMinPosition = minPosition - randChordMaker.getBotNotePositionDistanceFromRoot();
        int adjustedMaxPosition = maxPosition - randChordMaker.getTopNotePositionDistanceFromRoot();

        // get a random position between the adjusted min and max, and get the note from that position
        int randRootPosition = Rand.getRandInclusiveBetween(adjustedMinPosition, adjustedMaxPosition);
        int randRoot = chromaticNotePool.getNoteByPosition(randRootPosition);

        // create a chord by passing that random note as the root
        Chord chord = randChordMaker.createChord(randRoot);
        return chord;
    }


    public MusicMakerRandom setChordMakerPool(ChordMakerPool chordMakerPool) {
        this.chordMakerPool = chordMakerPool;
        return this;
    }

    public MusicMakerRandom setWhichHands(WhichHands whichHands) {
        this.whichHands = whichHands;
        int LEFT = 0;
        int RIGHT = 1;
        if (whichHands == WhichHands.LEFT) {
            handPool.add(LEFT);
        } else if (whichHands == WhichHands.RIGHT) {
            handPool.add(RIGHT);
        } else {
            handPool.add(LEFT);
            handPool.add(RIGHT);
        }
        return this;
    }

    public MusicMakerRandom setNote(KeySigNote note) {
        this.note = note;
        return this;
    }

    public MusicMakerRandom setMode(KeySigMode mode) {
        this.mode = mode;
        return this;
    }

    public MusicMakerRandom setLmin(int lmin) {
        this.lmin = lmin;
        return this;
    }

    public MusicMakerRandom setLmax(int lmax) {
        this.lmax = lmax;
        return this;
    }

    public MusicMakerRandom setRmin(int rmin) {
        this.rmin = rmin;
        return this;
    }

    public MusicMakerRandom setRmax(int rmax) {
        this.rmax = rmax;
        return this;
    }

    public MusicMakerRandom setLength(int length) {
        this.length = length;
        return this;
    }

    public MusicMakerRandom setHandPool(List<Integer> handPool) {
        this.handPool = handPool;
        return this;
    }
}
