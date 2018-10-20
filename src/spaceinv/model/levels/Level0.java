package spaceinv.model.levels;



import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.Bomber;
import spaceinv.model.ships.Frigate;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayList;
import java.util.List;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

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
    private final OuterSpace outerspace = new OuterSpace();
    private final Gun gun = new Gun(GAME_WIDTH / 2, GAME_HEIGHT - 75);
    private  List<AbstractSpaceShip>  shipsRow1, shipsRow2;
    private final List<AbstractSpaceShip> ships;

    public Level0()
    {
        //TODO move everything out of this constructor and make the variables final
        //Use the functions in LevelUtils
        shipsRow1 = LevelUtils.asList(new Bomber(40,20),8);
        shipsRow2 = LevelUtils.asList(new Frigate(40,60),6);
        shipsRow1 = LevelUtils.distribute(shipsRow1,10);
        shipsRow2 = LevelUtils.distribute(shipsRow2, 10);
        ships = LevelUtils.addAll(shipsRow1,shipsRow2);
//        for (int i = 1; i<10; i++){
//            if (i%2 == 1) {
//                ships.add(new Bomber(40 * i + 10, 20));
//            }else {
//                ships.add(new Frigate(40 * i + 10, 20));
//            }
//        }
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
        return new ShipFormation(ships,2);
    }
}
