package spaceinv.model.ships;


import spaceinv.model.IPositionable;
import spaceinv.model.projectiles.Projectile;

import java.util.List;
import java.util.Random;

import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Handle movement of all ships (one at the time)
 */
public class ShipFormation {

    private final double PADDING_LEFT = 100;
    private final double PADDING_RIGHT = GAME_WIDTH - PADDING_LEFT;
    private final double DOWN_STEP = 30;

    private static final Random rand = new Random();
    private final List<AbstractSpaceShip> ships;
    private Direction moveDir;

    public ShipFormation(List<AbstractSpaceShip> ships) {
        this.ships = ships;
        moveDir = Direction.RIGHT;
    }

    // TODO Some method to move the ships

    enum Direction {
        RIGHT, LEFT;
    }

    // TODO Some method to reove ship hit by rocket
    public boolean ckeckHit(Projectile proj) {

        if (proj.getSender().equals(AbstractSpaceShip.getSender())) {
            return false;
        }

        for (AbstractSpaceShip ship : ships) {
            if (ship.isColiding(proj)) {
                return true;
            }
        }
        return false;
    }

    public void removeShipOnHit(Projectile proj) {
        if (proj.getSender().equals(AbstractSpaceShip.getSender())) {
            return;
        }

        for (int i = ships.size() - 1; i <= 0; i--) {
            if (ships.get(i).isColiding(proj)) {
                ships.remove(i);
            }
        }
    }


    public int size() {
        return ships.size();
    }


    public List<AbstractSpaceShip> getShips() {
        return ships;
    }
}
