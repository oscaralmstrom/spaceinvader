package spaceinv.model.projectiles;

import spaceinv.model.Movable;

public abstract class Projectile extends Movable {

    private Sender sender;

    public Projectile(double x, double y, Sender _sender) {
        super(x, y);
        sender = _sender;
    }

    public enum Sender {
        GUN, INVADER;
    }

    public Sender getSender() {
        return sender;
    }
}
