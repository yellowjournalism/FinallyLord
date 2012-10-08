package utility;

import java.util.Random;

public class Rand {
    public static Random random = new Random();

    public static int randInt(int min, int max) {
        int randomNum = random.nextInt(max - min) + min;
        return randomNum;
    }

    public static int nextInt(int max) {
        return random.nextInt(max);
    }

    public static boolean oneIn(int x) {
        if (nextInt(x) == 0) {
            return true;
        }
        return false;
    }

}
