/*
 * This file is part of Finally Lord.
 *
 *      Finally Lord is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      Finally Lord is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with Finally Lord.  If not, see <http://www.gnu.org/licenses/>.
 */

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

    public static float nextFloat() {
        return random.nextFloat();
    }

}
