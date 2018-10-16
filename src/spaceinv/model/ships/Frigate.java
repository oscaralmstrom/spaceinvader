package spaceinv.model.ships;

/*
 *   Type of space ship
 */
public class Frigate extends AbstractSpaceShip  {

    public Frigate(double _posX, double _posY) {
        super(_posX, _posY);
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
