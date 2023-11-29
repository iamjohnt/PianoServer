package com.piano.server.game.music;

import com.piano.server.game.util.KeySigMode;

public class KeySignaturePatterns {

    int[] getModePattern(KeySigMode mode) {
        int half = 1;
        int whole = 2;
        int[] rtn = null;
        switch (mode) {
            case MAJOR -> {
                rtn = new int[] {whole, whole, half, whole, whole, whole, half};
            }
            case MINOR -> {
                rtn = new int[] {whole, half, whole, whole, half, whole, whole};
            }
        }
        return rtn;
    }

}
