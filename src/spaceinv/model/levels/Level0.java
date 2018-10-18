package spaceinv.model.levels;



import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.BattleCruiser;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayList;
import java.util.List;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;
import static spaceinv.model.levels.LevelUtils.*;

/*
    Basic level to test ships and movement of

 */

public class Level0 implements ILevel {

    /*private final List<AbstractSpaceShip> ships =
            addAll(
                    distribute(asList(null, 4), 5)
            );*/
            // TODO replace null above with some ship

    private final Ground ground = new Ground();
    private final OuterSpace outerspae = new OuterSpace();
    private final Gun gun = new Gun(GAME_WIDTH / 2, GAME_HEIGHT - 75);
    private final List<AbstractSpaceShip> ships = new ArrayList<>();

    public Level0()
    {
        //TODO move everything out of this constructor and make the variables final
        //Use the functions in LevelUtils

        for (int i = 1; i<10; i++){
            ships.add(new Bomber(40*i +10, 20, 2));
        }
    }

    @Override
    public OuterSpace getOuterSpace() {
        return outerspae;
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
        return new ShipFormation(ships);
    }

}
