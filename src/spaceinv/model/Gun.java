package spaceinv.model;


import spaceinv.model.projectiles.Projectile;
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
    private static final Projectile.Sender senderType = Projectile.Sender.GUN;
    private static final int maxCooldow = 20; //amount of cycles until gun can be fired again
    private int currentCooldown;

    public Gun(double x, double y) {
        super(x, y);
        currentCooldown = 0;
    }

    //fire will return null of the gun has not cooled down yet
    public Movable fire() {
        if (currentCooldown > 0) {
            return null;
        }

        Movable shot = new Rocket(getSpeedX(), getSpeedY(), senderType);
        shot.setSpeed(0, Rocket.MAX_SPEED);

        currentCooldown = maxCooldow;

        return shot;
    }

    public void decCooldown() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
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
