package spaceinv.model.projectiles;

public class Explosion extends Projectile {

    public Explosion(Projectile proj) {
        super(proj.getX() + proj.getWidth() - proj.getEXPLOSION_DIAMETER(),
                proj.getY() + proj.getWidth() / 2 - proj.getEXPLOSION_DIAMETER(),
                proj.getSender(), 0, proj.getEXPLOSION_DIAMETER());
    }

    @Override
    public double getWidth() {
        return getEXPLOSION_DIAMETER();
    }

    @Override
    public double getHeight() {
        return getEXPLOSION_DIAMETER();
    }
}
