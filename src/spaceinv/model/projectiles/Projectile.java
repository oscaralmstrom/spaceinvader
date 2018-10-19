package spaceinv.model.projectiles;

import spaceinv.model.Movable;

public abstract class Projectile extends Movable {

    private Sender sender;
    private double EXPLOSION_DIAMETER;

    public Projectile(double x, double y, Sender _sender, double maxSpeed, double explRad) {
        super(x, y);
        sender = _sender;
        if (sender == Sender.INVADER) {
            setSpeed(0, maxSpeed);
        } else {
            setSpeed(0, -maxSpeed);
        }
        EXPLOSION_DIAMETER = explRad;
    }

    protected double getEXPLOSION_DIAMETER() {
        return EXPLOSION_DIAMETER;
    }

    public enum Sender {
        GUN, INVADER;
    }

    public Projectile getExplosion() {
        return new Explosion(this);
    }

    public Sender getSender() {
        return sender;
    }
}
