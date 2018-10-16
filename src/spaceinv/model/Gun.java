package spaceinv.model;


import spaceinv.model.projectiles.Rocket;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
 * A Gun for the SpaceInv game
 */
public class Gun extends Movable {

    public static final double MAX_SPEED = 2;

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
        return 0;
    }

    @Override
    public double getHeight() {
        return 0;
    }
}
