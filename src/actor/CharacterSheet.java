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

package actor;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 10/24/12
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class CharacterSheet {
    int[] stats;
    int HD;
    int hp, maxhp;
    int attack;


    public CharacterSheet() {
        stats = new int[Stat.STAT_MAX.val()];
        HD = 10;
        hp = getStat(Stat.CON) * 10;
        maxhp = hp;
        attack = 10;
    }

    public int getStat(Stat stat) {
        return stats[stat.val()];
    }

    public double physicalCondition() {
        return (double) hp / (double) maxhp;
    }

    public double percievedStrength() {
        return (double) getStat(Stat.STR) * physicalCondition();
    }
}
