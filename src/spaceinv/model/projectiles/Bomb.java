package spaceinv.model.projectiles;


import spaceinv.model.Movable;

// Dropped by the ships
public class Bomb extends Projectile {

    private static final double MAX_SPEED = 5;
    private static final double BOMB_WIDTH = 10;
    private static final double BOMB_HEIGHT = 10;

    public Bomb(double x, double y, Projectile.Sender sender) {
        super(x, y, sender, MAX_SPEED, 25);
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
