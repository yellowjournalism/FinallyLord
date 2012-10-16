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
 * Date: 10/15/12
 * Time: 12:57 PM
 * To change this template use File | Settings | File Templates.
 */

public abstract class Action {

    private Actor actor; //Current actor to do action on, remember to set the new actor after the prev has been ran!
    private static Action action;

    public Action() {

    }

    public abstract int rate(Actor a);

    public abstract void execute();

}
