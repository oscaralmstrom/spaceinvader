package spaceinv.model;


import spaceinv.model.projectiles.Rocket;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
 * A Gun for the SpaceInv game
 */
public class Gun extends Movable {

    public static final double MAX_SPEED = 2;
    private static final double GUN_WIDTH = 42;
    private static final double GUN_HEIGHT = 55;

    Gun(double x, double y) {
        super(x, y);
    }

    public Movable fire() {
        Movable shot = new Rocket(getSpeedX(), getSpeedY());
        shot.setSpeed(0, Rocket.MAX_SPEED);
        return shot;
    }

    @Override
    public double getWidth() {
        return GUN_WIDTH;
    }

    @Override
    public double getHeight() {
        return GUN_HEIGHT;
    }
}
