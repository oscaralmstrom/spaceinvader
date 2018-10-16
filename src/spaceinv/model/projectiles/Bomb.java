package spaceinv.model.projectiles;


import spaceinv.model.Movable;

// Dropped by the ships
public class Bomb extends Movable {

    private final double MAX_SPEED = 20;
    private static final double BOMB_WIDTH = 10;
    private static final double BOMB_HEIGHT = 10;

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

    public double getMAX_SPEED() {
        return MAX_SPEED;
    }
}
