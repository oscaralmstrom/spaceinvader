package spaceinv.model.ships;

import spaceinv.model.Movable;
import spaceinv.model.projectiles.Projectile;

import java.util.Random;


/*
 * An alien attacker for the space invaders game
 *
 * Base class for all ships
 */

public abstract class AbstractSpaceShip extends Movable {

    private final static Projectile.Sender senderType = Projectile.Sender.INVADER;
    private static final double SHIP_WIDTH = 40;
    private static final double SHIP_HEIGHT = 30;

    private static Random rand = new Random();   // TODO

    //private double minX;  // min and max for ship to move i x-dir
    //private double maxX;

    public AbstractSpaceShip(double _posX, double _posY) {
        super(_posX, _posY);
    }

    // To be overridden
    public abstract int getPoints();

    // To be overridden
    public abstract Object copyOf();

    public double getWidth() {
        return SHIP_WIDTH;
    }

    public double getHeight() {
        return SHIP_HEIGHT;
    }

    public static Projectile.Sender getSender() {
        return senderType;
    }

    public static double getShipWidth() {
        return SHIP_WIDTH;
    }

    public static double getShipHeight() {
        return SHIP_HEIGHT;
    }
    // For ships moving back and forth
    //public void setMoveInterval(double minX, double maxX) {
    // TODO
    //}

}
