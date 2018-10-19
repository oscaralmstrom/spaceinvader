package spaceinv.model.ships;

import spaceinv.model.projectiles.Bomb;
import spaceinv.model.projectiles.Projectile;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {
    public Bomber(double _posX, double _posY) {
        super(_posX, _posY);
    }

    @Override
    public Bomb fire() {
        return new Bomb(getX(), getY(), getSender());
    }

    @Override
    public Object copyOf() {
        return new Bomber(getX(), getY());
    }

    @Override
    public int getPoints() {
        return 2;
    }
}
