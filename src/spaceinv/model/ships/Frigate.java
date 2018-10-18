package spaceinv.model.ships;

import spaceinv.model.projectiles.Rocket;

/*
 *   Type of space ship
 */
public class Frigate extends AbstractSpaceShip  {

    public Frigate(double _posX, double _posY, int pointsIfKilled) {
        super(_posX, _posY, pointsIfKilled);
    }

    @Override
    public Rocket fire() {
        return new Rocket(getX(), getY(), getSender());
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
