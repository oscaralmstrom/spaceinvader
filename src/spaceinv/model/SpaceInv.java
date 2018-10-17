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
    private GameState gameState;

    // TODO
    //private final List<AbstractSpaceShip> ships;

    // TODO
    private final Ground ground;           // Border for bombs
    private final OuterSpace outerSpace;   // Border for rocket
    private final Gun gun;
    private final ShipFormation formation;
    private double formationSpeed;

    private int points;
    private final List<Projectile> projectiles;

    // Timing. All timing handled here!
    private long timeForLastMove;
    private long timeForlastFire;
    private long shipMoveDelay = TENTH_SEC;

    public SpaceInv(List<AbstractSpaceShip> spaceShips, Gun theGun, Ground _ground, OuterSpace backGround) {
        gun = theGun;
        formation = new ShipFormation(spaceShips);
        formationSpeed = 2;
        ground = _ground;
        outerSpace = backGround;
        projectiles = new ArrayList<>();
        gameState = GameState.RUNNING;
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
        Projectile temp = formation.spawnInvaderProjectiles();
        if (temp != null) {
            projectiles.add(temp);
        }
        formation.move(formationSpeed);


        for (int i = projectiles.size() - 1; i >= 0; i--) {
            projectiles.get(i).move();

            switch (projectiles.get(i).getSender()) {
                case GUN:
                    if (projectiles.get(i).isColiding(outerSpace)) {
                        projectiles.remove(i);
                        break;
                    }

                    if (formation.ckeckHit(projectiles.get(i))) {
                        //TODO If projectile was a bomb, trigger explosion here
                        formation.removeShipOnHit(projectiles.get(i));
                        projectiles.remove(i);
                    }
                    break;
                case INVADER:
                    if (projectiles.get(i).isColiding(ground)) {
                        projectiles.remove(i);
                        continue;
                    }

                    if (projectiles.get(i).isColiding(gun)) {
                        gun.hit();
                        projectiles.remove(i);
                    }
                    break;
            }
        }

        if (gun.getHealth() <= 0) {
            gameState = GameState.LOOSE;
            return;
        }
        else if(projectiles.size() == 0)
        {
            gameState = GameState.WIN;
        }
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

    public List<IPositionable> getPositionables() {
        List<IPositionable> ps = new ArrayList<>();
        ps.add(gun);
        ps.addAll(formation.getShips());
        ps.add(ground);
        ps.addAll(projectiles);
        ps.add(outerSpace);

        return ps;
    }

    public int getPoints() {
        return points;
    }

    public GameState getGameState() {
        return gameState;
    }

    public enum GameState{
        RUNNING, WIN, LOOSE;
    }

}
