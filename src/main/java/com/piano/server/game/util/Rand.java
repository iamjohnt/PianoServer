package com.piano.server.game.util;

import java.util.List;
import java.util.Random;

public class Rand {

    public static int getRandInclusiveBetween(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min to generate random number between them");
        }
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size()); // Generate a random index within the list size
        return list.get(randomIndex);
    }
}
