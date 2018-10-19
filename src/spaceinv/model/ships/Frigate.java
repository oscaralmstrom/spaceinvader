package spaceinv.model.ships;

import spaceinv.model.projectiles.Rocket;

/*
 *   Type of space ship
 */
public class Frigate extends AbstractSpaceShip  {

    public Frigate(double _posX, double _posY) {
        super(_posX, _posY);
    }

    @Override
    public Rocket fire() {
        return new Rocket(getX(), getY(), getSender());
    }

    @Override
    public int getPoints() {
        return 3; //Decide points
    }

    @Override
    public Object copyOf() {
        return null;
    }
}
