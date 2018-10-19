package spaceinv.model.ships;

import spaceinv.model.projectiles.Projectile;

/*
 *   Type of space ship
 */
public class BattleCruiser extends AbstractSpaceShip{
    public BattleCruiser(double _posX, double _posY) {
        super(_posX, _posY);
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public Projectile fire() {
        return null;
    }

    @Override
    public Object copyOf() {
        return null;
    }

    @Override
    public double getWidth() {
        return super.getWidth();
    }

    @Override
    public double getHeight() {
        return super.getHeight();
    }
}
