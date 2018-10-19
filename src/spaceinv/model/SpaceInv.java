package spaceinv.model;

import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.projectiles.Projectile;
import spaceinv.model.levels.ILevel;
import spaceinv.model.projectiles.Bomb;
import spaceinv.model.projectiles.Explosion;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayList;
import java.util.List;

/*
 * Logic for the SpaceInv Game
 * Model class representing the "overall" game logic
 *
 * Nothing visual here
 *
 * See:
 * - week6/samples/catchtherain
 */
public class SpaceInv {

    public static final double GAME_WIDTH = 800;
    public static final double GAME_HEIGHT = 400;

    public static final long ONE_SEC = 1_000_000_000;
    public static final long HALF_SEC = 500_000_000;
    public static final long TENTH_SEC = 100_000_000;
    private GameState gameState;

    // TODO
    //private final List<AbstractSpaceShip> ships;

    // TODO
    private final Ground ground;           // Border for bombs
    private final OuterSpace outerSpace;   // Border for rocket
    private final Gun gun;
    private ShipFormation formation;
    private double formationSpeed;

    private int points;
    private final List<Projectile> projectiles;
    private final List<Projectile> explosions;

    // Timing. All timing handled here!
    private long timeForLastMove;
    private long timeForlastFire;
    private long shipMoveDelay = TENTH_SEC;

    public SpaceInv(ILevel level/*List<AbstractSpaceShip> spaceShips, Gun theGun, Ground _ground, OuterSpace backGround*/) {
        gun = level.getGun();
        formation = level.getFormation();
        ground = level.getGround();
        outerSpace = level.getOuterSpace();

        points = 0;
        formationSpeed = 2;
        projectiles = new ArrayList<>();
        explosions = new ArrayList<>();
        gameState = GameState.RUNNING;
    }

    // ------ Game loop (called by timer) -----------------

    public void update(long now) {
        moveGun();
        moveFormation();

       outerLoop: for (int i = projectiles.size() - 1; i >= 0; i--) {
            projectiles.get(i).move();

            switch (projectiles.get(i).getSender()) {
                case GUN:
                    if (checkGunProjectile(i)){break outerLoop;}
                    break;
                case INVADER:
                    checkInvaderProjectile(i);
                    break;
            }
        }

        if (gun.getHealth() <= 0) {
            gameState = GameState.LOSE;
            return;
        } else if (formation.size() == 0) {
            gameState = GameState.WIN;
        }
    }

    private void moveFormation() {
        Projectile temp = formation.spawnInvaderProjectiles();
        if (temp != null) {
            projectiles.add(temp);
        }
        formation.move(formationSpeed);
    }

    private void moveGun() {
        if (!movesOutOfWindow(gun)) {
            gun.move();
        } else {
            if (gun.getX() > GAME_WIDTH / 2) {
                gun.setPosition(GAME_WIDTH - gun.getWidth(), gun.getY());
            }
        }
        gun.decCooldown();
    }

    private void checkInvaderProjectile(int i) {
        boolean groundHit = projectiles.get(i).isColliding(ground);
        boolean gunHit = projectiles.get(i).isColliding(gun);

        if (!groundHit && !gunHit) {
            return;
        }

        if (groundHit) {
            EventService.add(new Event(Event.Type.BOMB_HIT_GROUND));
        } else if (gunHit) {
            gun.hit();
            EventService.add(new Event(Event.Type.BOMB_HIT_GUN));
        }

        explosions.add(new Explosion(projectiles.get(i)));
        projectiles.remove(i);
    }

    private boolean checkGunProjectile(int i) {
        boolean spaceHit = projectiles.get(i).isColliding(outerSpace);
        boolean formationHit = formation.checkHit(projectiles.get(i));
        boolean collateralDamage = false;

        if (!spaceHit && !formationHit) {
            return collateralDamage;
        }

        if (formationHit) {
            collateralDamage = true;
            if (projectiles.get(i) instanceof Bomb) {
                //Replaces bomb with explosion
                projectiles.set(i, explosions.get(explosions.size() - 1));
                collateralDamage = false;
            }
            EventService.add(new Event(Event.Type.ROCKET_HIT_SHIP));
            points += formation.removeShipOnHit(projectiles.get(i));
            explosions.add(new Explosion(projectiles.get(i)));
        }
        projectiles.remove(i);
        return collateralDamage;
    }

    //-------Helping methods--------------------------

    //Returns true if the object will move out of window
    public boolean movesOutOfWindow(Movable obj) {
        if (obj.getX() + obj.getSpeedX() + obj.getWidth() > GAME_WIDTH || obj.getX() + obj.getSpeedX() < 0) {
            return true;
        }

        if (obj.getSpeedY() + obj.getSpeedY() + obj.getHeight() > GAME_HEIGHT || obj.getY() + obj.getSpeedY() < 0) {
            return true;
        }
        return false;
    }


    // ------------- Increase pressure on player
    private boolean finalSpeed = false;
    private boolean incSpeed = false;

    // ---------- Interaction with GUI  -------------------------

    public void fireGun() {
        Projectile shot = gun.fire();
        if (shot != null) {
            EventService.add(new Event(Event.Type.ROCKET_LAUNCHED));
            projectiles.add(shot);
        }
    }

    public void moveGunLeft() {
        gun.setSpeed(-gun.MAX_SPEED, 0);
    }

    public void moveGunRight() {
        gun.setSpeed(gun.MAX_SPEED, 0);
    }

    public void stopGun() {
        gun.setSpeed(0, 0);
    }

    // --------- Send everything to be rendered --------------

    public void newFormation(ShipFormation form) {
        if (form != null) {
            formation = form;
            gameState = GameState.RUNNING;
        }
    }

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
        ps.add(gun);
        ps.addAll(formation.getShips());
        ps.add(ground);
        ps.addAll(projectiles);
        ps.add(outerSpace);

        return ps;
    }

    public List<IPositionable> getExplosions() {
        List<IPositionable> ps = new ArrayList<>();
        ps.addAll(explosions);
        explosions.clear();
        return ps;
    }

    public int getPoints() {
        return points;
    }

    public GameState getGameState() {
        return gameState;
    }

    public enum GameState {
        RUNNING, WIN, LOSE;
    }

}
