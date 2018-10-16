package spaceinv.model;

import spaceinv.event.Event;
import spaceinv.event.EventService;
import spaceinv.model.levels.ILevel;
import spaceinv.model.projectiles.Projectile;
import spaceinv.model.projectiles.Rocket;
import spaceinv.model.ships.AbstractSpaceShip;
import spaceinv.model.ships.ShipFormation;
import spaceinv.model.statics.Ground;
import spaceinv.model.statics.OuterSpace;

import java.util.ArrayList;
import java.util.List;

import static spaceinv.model.Gun.MAX_SPEED;

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
    private boolean IS_RUNNING;

    // TODO
    //private final List<AbstractSpaceShip> ships;

    // TODO
    private final Ground ground;           // Border for bombs
    private final OuterSpace outerSpace;   // Border for rocket
    private final Gun gun;
    private final ShipFormation formation;

    private int points;
    private final List<Projectile> projectiles;

    // Timing. All timing handled here!
    private long timeForLastMove;
    private long timeForlastFire;
    private long shipMoveDelay = TENTH_SEC;

    public SpaceInv(List<AbstractSpaceShip> spaceShips, Gun theGun, Ground _ground, OuterSpace backGround) {
        gun = theGun;
        formation = new ShipFormation(spaceShips);
        ground = _ground;
        outerSpace = backGround;
        projectiles = new ArrayList<>();
        IS_RUNNING = true;
    }

    // ------ Game loop (called by timer) -----------------

    public void update(long now) {
        // TODO the game loop
        if (!movesOutOfWindow(gun)) {
            gun.move();
        } else {
            if (gun.getX() > GAME_WIDTH / 2) {
                gun.setPosision(GAME_WIDTH - gun.getWidth(), gun.getY());
            }
        }
        gun.decCooldown();

        formation.move();

        for (int i = projectiles.size() - 1; i >= 0; i--) {
            if (movesOutOfWindow(projectiles.get(i))) {
                projectiles.remove(i);
                continue;
            }

            projectiles.get(i).move();

            if (projectiles.get(i).isColiding(gun)) {
                gun.hit();
                projectiles.remove(i);
            } else if (formation.ckeckHit(projectiles.get(i))) {
                //TODO If projectile was a bomb, trigger explosion here
                formation.removeShipOnHit(projectiles.get(i));
            }
        }

        if (gun.getHealth() <= 0) {
            IS_RUNNING = false;
            return;
        }
    }

    }

    // ------------- Increase pressure on player
    private boolean finalSpeed = false;
    private boolean incSpeed = false;

    // ---------- Interaction with GUI  -------------------------

    public void fireGun() {
       // TODO
    }

    public void moveGunLeft() {
        // TODO
    }

    public void moveGunRight() {
        // TODO
    }

    public void stopGun() {
       // TODO
    }

    // --------- Send everything to be rendered --------------

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
       // TODO Add all to be rendered
        if (rocket != null) {
            // TODO posables.add(rocket);
        }
        return ps;
    }

    public int getPoints() {
        return points;
    }


}
