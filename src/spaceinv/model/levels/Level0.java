package spaceinv.model.levels;



import spaceinv.model.Gun;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.BattleCruiser;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.List;

import static spaceinv.model.levels.LevelUtils.*;

/*
    Basic level to test ships and movement of

 */
public class Level0 implements ILevel {

    private final List<AbstractSpaceShip> ships =
            addAll(
                    distribute(asList(null, 4), 5)
            );
            // TODO replace null above with some ship

    @Override
    public OuterSpace getOuterSpace() {
        return null; // TODO new OuterSpace(0); // Dummy for testing usage
    }

    @Override
    public Ground getGround() {
        return null; // TODO new Ground(0); // Dummy for testing usage
    }

    @Override
    public Gun getGun() {
        return null; // TODO new Gun(0, 0, 0); // Dummy for testing usage
    }

    @Override
    public ShipFormation getFormation() {
        return new ShipFormation(ships);
    }

}
