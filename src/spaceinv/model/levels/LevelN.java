package spaceinv.model.levels;


import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.Frigate;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;
import sun.plugin.javascript.navig.LinkArray;

import java.util.ArrayList;
import java.util.List;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Basic level to test ships and movement of

 */

public class LevelN implements ILevel {

    // TODO replace null above with some ship

    private final Ground ground = new Ground();
    private final OuterSpace outerspace = new OuterSpace();
    private final Gun gun = new Gun(GAME_WIDTH / 2, GAME_HEIGHT - 75);
    private final List<List<AbstractSpaceShip>> shipRows = new ArrayList<>();
    private final List<AbstractSpaceShip> ships;
    private final int diff;
    private static final double spacing = 10;

    public LevelN(int diff) {
        //TODO move everything out of this constructor and make the variables final
        //Use the functions in LevelUtils

        shipRows.add(LevelUtils.distribute(LevelUtils.asList(new Bomber(40, 20), diff < 4 ? 2 * diff : 8), spacing));
        if (diff > 4) {
            shipRows.add(LevelUtils.distribute(LevelUtils.asList(new Frigate(40, 60), diff < 8 ? 2 * (diff-4) : 8), spacing));
        }
        if (diff > 8) {
            shipRows.add(LevelUtils.distribute(LevelUtils.asList(new Frigate(40, 100), diff < 12 ? (2 * diff-8) : 8), spacing));
        }

        ships = LevelUtils.addAll(shipRows);
        this.diff = diff;
    }

    @Override
    public OuterSpace getOuterSpace() {
        return outerspace;
    }

    @Override
    public Ground getGround() {
        return ground;
    }

    @Override
    public Gun getGun() {
        return gun;
    }

    @Override
    public ShipFormation getFormation() {
        return new ShipFormation(ships, 2, diff);
    }
}
