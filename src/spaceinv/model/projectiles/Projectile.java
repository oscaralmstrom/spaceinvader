package spaceinv.model.projectiles;

import spaceinv.model.Movable;

public abstract class Projectile extends Movable {

    private Sender sender;
    private double EXPLOSION_DIAMITER;

    public Projectile(double x, double y, Sender _sender, double maxSpeed, double explRad) {
        super(x, y);
        sender = _sender;
        if (sender == Sender.INVADER) {
            setSpeed(0, maxSpeed);
        } else {
            setSpeed(0, -maxSpeed);
        }
        EXPLOSION_DIAMITER = explRad * 2;
    }

    protected double getEXPLOSION_DIAMITER() {
        return EXPLOSION_DIAMITER;
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
