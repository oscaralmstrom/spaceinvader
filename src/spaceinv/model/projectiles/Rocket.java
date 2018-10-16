package spaceinv.model.projectiles;


import spaceinv.model.Movable;

// Shoot by the gun
public class Rocket extends Movable {

    public static final double MAX_SPEED = 20;

    public Rocket(double x, double y) {
        super(x, y);
    }

    @Override
    public double getWidth() {
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }
}
