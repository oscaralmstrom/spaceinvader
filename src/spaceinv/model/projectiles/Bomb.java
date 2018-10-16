package spaceinv.model.projectiles;


import spaceinv.model.Movable;

// Dropped by the ships
public class Bomb extends Movable {

    public static final double MAX_SPEED = 15;
    public static final double BOMB_WIDTH = 10;
    public static final double BOMB_HEIGHT = 10;

    public Bomb(double x, double y) {
        super(x, y);
    }

    @Override
    public double getWidth() {

        return BOMB_WIDTH;
    }

    @Override
    public double getHeight() {
        return BOMB_HEIGHT;
    }

}
