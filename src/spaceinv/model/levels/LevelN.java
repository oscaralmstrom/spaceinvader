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
    private List<List<AbstractSpaceShip>> shipRows;
    private final List<AbstractSpaceShip> ships;
    private static final double spacing = 10;

        //TODO move everything out of this constructor and make the variables final
        //Use the functions in LevelUtils
        shipsRow1 = LevelUtils.asList(new Bomber(40, 20), 8);
        shipsRow2 = LevelUtils.asList(new Frigate(40, 60), 6);
        shipsRow1 = LevelUtils.distribute(shipsRow1, 10);
        shipsRow2 = LevelUtils.distribute(shipsRow2, 10);
        ships = LevelUtils.addAll(shipsRow1, shipsRow2);

        ships.addAll(LevelUtils.distribute(LevelUtils.asList(sourceShips.get(sourceShips.size()-1), diff), spacing));
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
        return new ShipFormation(ships, 2, 2);
    }
}
