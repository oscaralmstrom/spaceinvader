package spaceinv.model.ships;

import spaceinv.model.projectiles.Bomb;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {
//    public Bomb createBomb{
//
//        return new Bomb(getX(), getY());
//    }

    @Override
    public int getPoints() {
        return 0; //Decide points
    }

    @Override
    public Object copyOf() {
        return null;
    }


}
