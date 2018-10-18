package spaceinv.model.projectiles;


import spaceinv.model.Movable;

// Shoot by the gun
public class Rocket extends Projectile {

    public static final double MAX_SPEED = 20;
    private static final double ROCKET_WIDTH = 13;
    private static final double ROCKET_HEIGHT = 35;

    public Rocket(double x, double y, Sender sender) {
        super(x, y, sender, MAX_SPEED, 17.5);
    }

    @Override
    public double getWidth() {
        return ROCKET_WIDTH;
    }

    @Override
    public double getHeight() {
        return ROCKET_HEIGHT;
    }
}
