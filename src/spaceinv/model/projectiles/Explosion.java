package spaceinv.model.projectiles;

public class Explosion extends Projectile {

    public Explosion(Projectile proj) {
        super(proj.getX() + proj.getWidth() - proj.getEXPLOSION_DIAMITER() / 2,
                proj.getY() + proj.getWidth() / 2 - proj.getEXPLOSION_DIAMITER() / 2,
                proj.getSender(), 0, proj.getEXPLOSION_DIAMITER() / 2);
    }

    @Override
    public double getWidth() {
        return getEXPLOSION_DIAMITER();
    }

    @Override
    public double getHeight() {
        return getEXPLOSION_DIAMITER();
    }
}
