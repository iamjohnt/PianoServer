package com.piano.server.game.util;

import java.util.List;

public class ChordPattern {

    private static final int OCT = 7;
    
    // note
    public static final int[] NOTE = new int[] {1};
    
    // intervals
    public static final int[] INTERVAL_SECOND = new int[] {0,1};
    public static final int[] INTERVAL_THIRD = new int[] {0,2};
    public static final int[] INTERVAL_FOURTH = new int[] {0,3};
    public static final int[] INTERVAL_FIFTH = new int[] {0,4};
    public static final int[] INTERVAL_SIXTH = new int[] {0,5};
    public static final int[] INTERVAL_SEVENTH = new int[] {0,6};
    public static final int[] INTERVAL_OCTAVE = new int[] {0,7};

    // triads
    public static final int[] TRIAD = new int[] {0,2,4};
    public static final int[] TRIAD_SUS_2 = new int[] {0,1,4};
    public static final int[] TRIAD_SUS_4 = new int[] {0,3,4};
    
    // triad inversions
    public static final int[] TRIAD_INVERSION_2_UP = new int[] {2,5,0+OCT};
    public static final int[] TRIAD_INVERSION_2_DOWN = new int[] {2-OCT,5-OCT,0};
    public static final int[] TRIAD_INVERSION_3_UP = new int[] {4,0+OCT,2+OCT};
    public static final int[] TRIAD_INVERSION_3_DOWN = new int[] {4-OCT,0,2};

    // tetrads
    public static final int[] TETRAD = new int[] {0,2,4,0+OCT};
    public static final int[] TETRAD_INVERTED = new int[] {5-OCT,0,2,4};
    public static final int[] TETRAD_SEVENTH = new int[] {0,2,4,6};
}
