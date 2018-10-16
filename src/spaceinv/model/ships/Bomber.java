package spaceinv.model.ships;

import spaceinv.model.projectiles.Bomb;
import spaceinv.model.projectiles.Projectile;

/*
 *   Type of space ship
 */
public class Bomber extends AbstractSpaceShip {
    private final static Projectile.Sender senderType = Projectile.Sender.INVADER;
    public Bomber(double _posX, double _posY) {
        super(_posX, _posY);
    }

    public Bomb createBomb() {
        return new Bomb(getX(), getY(), senderType);
    }

    @Override
    public int getPoints() {
        return 0; //Decide points
    }

    @Override
    public Object copyOf() {
        return null;
    }


}
