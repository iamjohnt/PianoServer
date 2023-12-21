package com.piano.server.stomp;

import com.piano.server.game.util.ChordPool;
import com.piano.server.game.util.WhichHands;
import com.piano.server.stomp.submission.GameSettingsSubmission;

public class GameSettingsSubmissionValidator {

    private static final int PIANO_MIN = 21;
    private static final int PIANO_MAX = 108;

    public boolean isValidGameSettings(GameSettingsSubmission s) {

        boolean isEnumsNotNull = s.getKeySigNote() != null && s.getKeySigMode() != null && s.getChordPool() != null && s.getWhichHands() != null;
        if (isEnumsNotNull == false) {
            return false;
        }

        boolean isMinMaxValid = isMinMaxValid(s.getWhichHands(), s.getLeftMin(), s.getLeftMax(), s.getRightMin(), s.getRightMax());

        boolean isLengthWithin100 = s.getLength() <= 100;

        boolean isLeftRangeEnough = isRangeEnough(s.getChordPool(), s.getLeftMin(), s.getLeftMax());

        boolean isRightRangeEnough = isRangeEnough(s.getChordPool(), s.getRightMin(), s.getRightMax());

        return isMinMaxValid && isLengthWithin100 && isLeftRangeEnough && isRightRangeEnough;
    }


    private boolean isMinMaxValid(WhichHands hand, int lmin, int lmax, int rmin, int rmax) {
        boolean isValid = true;
        switch (hand) {
            case LEFT -> {
                isValid &= lmin >= PIANO_MIN;
                isValid &= lmax <= PIANO_MAX;
                isValid &= lmin <= lmax;
            }
            case RIGHT -> {
                isValid &= rmin >= PIANO_MIN;
                isValid &= rmax <= PIANO_MAX;
                isValid &= rmin <= rmax;
            }
            case BOTH -> {
                isValid &= lmin >= PIANO_MIN;
                isValid &= lmax <= PIANO_MAX;
                isValid &= lmin <= lmax;
                isValid &= rmin >= PIANO_MIN;
                isValid &= rmax <= PIANO_MAX;
                isValid &= rmin <= rmax;
            }
        }
        return isValid;
    }

    private boolean isRangeEnough(ChordPool pool, int min, int max) {
        int range = max - min;
        boolean isRangeEnough = false;
        switch (pool) {
            case NOTE -> isRangeEnough = range >= 0;
            case INTERVAL -> isRangeEnough = range >= 12;
            case TRIAD -> isRangeEnough = range >= 9;
            case TETRAD -> isRangeEnough = range >= 12;
            case NOTE_INTERVAL -> isRangeEnough = range >= 12;
            case NOTE_INTERVAL_TRIAD -> isRangeEnough = range >= 12;
            case NOTE_INTERVAL_TRIAD_TETRAD -> isRangeEnough = range >= 12;
        }
        return isRangeEnough;
    }

}
