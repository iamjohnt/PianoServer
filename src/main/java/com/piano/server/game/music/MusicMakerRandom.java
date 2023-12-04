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
    private List<Integer> handPool;
    private ChordMakerPool chordMakerPool;

    public MusicMakerRandom(Config config) {
        this.handPool = new ArrayList<>();
        extractConfigToVars(config);
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

        // setup chord maker pool
        ChordMakerGroup randChordMakerGroup = Rand.getRandomElement(chordMakerPool.getChordMakerGroups());
        ChordMaker randChordMaker = Rand.getRandomElement(randChordMakerGroup.getChordMakers());

        // adjust the min and max positions, so i can fit the chord properly
        int minPosition = -12345;
        int maxPosition = -12345;
        try {
            minPosition = notePool.getPositionByNote(min);
            maxPosition = notePool.getPositionByNote(max);
        } catch (NoteOutOfBoundsException e) {
            e.printStackTrace();
        }
        int adjustedMinPosition = minPosition + randChordMaker.getBotNotePositionDistanceFromRoot();
        int adjustedMaxPosition = maxPosition - randChordMaker.getTopNotePositionDistanceFromRoot();

        // get a random position between the adjusted min and max, and get the note from that position
        int randRootPosition = Rand.getRandInclusiveBetween(adjustedMinPosition, adjustedMaxPosition);
        int randRoot = notePool.getNoteByPosition(randRootPosition);

        // create a chord by passing that random note as the root
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
            handPool.add(LEFT);
        } else if (config.getHands() == WhichHands.RIGHT) {
            handPool.add(RIGHT);
        } else {
            handPool.add(LEFT, RIGHT);
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
