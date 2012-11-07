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

package logic;

import actor.Actor;
import actor.Player;

/**
 * Created with IntelliJ IDEA.
 * User: hankbrobeck
 * Date: 11/5/12
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class DamageSystem {
    Player player;

    public DamageSystem(Player p) {
        this.player = p;

    }

    public boolean playerAttack(Actor a) {
        int dmg = player.getCharacterSheet().getAttack();
        a.getSheet().modHP(-dmg);
        return true;//TODO implement hit chance, etc and return false when failed
    }

    public boolean attackPlayer(Actor attacker) {
        int dmg = attacker.getSheet().getAttack();
        player.getCharacterSheet().modHP(-dmg);
        return true; //TODO Same as above.
    }
}
