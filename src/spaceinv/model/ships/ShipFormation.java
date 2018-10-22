package spaceinv.model.ships;


import spaceinv.model.IPositionable;
import spaceinv.model.projectiles.Projectile;

import java.util.List;
import java.util.Random;

import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Handle movement of all ships (one at the time)
 */
public class ShipFormation {

    private final double PADDING_LEFT = 100;
    private final double PADDING_RIGHT = GAME_WIDTH - PADDING_LEFT;
    private final double DOWN_STEP;
    private final double chanceOfShooting;

    private static final Random rand = new Random();
    private final List<AbstractSpaceShip> ships;
    private Direction moveDir;
    private double speed;
    private final double speedInc;

    public ShipFormation(List<AbstractSpaceShip> ships, double startSpeed, int diff) {
        this.ships = ships;
        DOWN_STEP = ships.get(0).getHeight();
        moveDir = Direction.RIGHT;
        speed = startSpeed;
        speedInc = diff*0.2;
        chanceOfShooting = diff * 0.01;
    }

    // TODO Some method to move the ships
    public void move() {

        switch (moveDir) {
            case RIGHT:
                speed = Math.abs(speed);

                if (movesOutOfWindow(speed)) {
                    stepDown();
                    moveDir = Direction.LEFT;
                } else {
                    moveAllX(speed);
                }
                break;
            case LEFT:
                speed = -Math.abs(speed);

                if (movesOutOfWindow(speed)) {
                    stepDown();
                    moveDir = Direction.RIGHT;
                } else {
                    moveAllX(speed);
                }
                break;
        }

    }

    public Projectile spawnInvaderProjectiles() {
        if (rand.nextDouble() < chanceOfShooting) {
            return ships.get(rand.nextInt(ships.size())).fire();
        }
        return null;
    }

    private boolean movesOutOfWindow(double speedX) {
        for (AbstractSpaceShip ship : ships) {
            if (ship.getX() + ship.getWidth() + speedX > GAME_WIDTH || ship.getX() + speedX < 0) {
                return true;
            }
        }
        return false;
    }

    private void stepDown() {

        for (AbstractSpaceShip ship : ships) {
            ship.setSpeed(0, DOWN_STEP);
            ship.move();
            ship.setSpeed(0, 0);
        }
        speed = Math.abs(speed) + speedInc;
    }

    private void moveAllX(double speedX) {
        for (AbstractSpaceShip ship : ships) {
            ship.setSpeed(speedX, 0);
            ship.move();
            ship.setSpeed(0, 0);
        }
    }

    enum Direction {
        RIGHT, LEFT;
    }

    // TODO Some method to reove ship hit by rocket
    public boolean checkHit(Projectile proj) {

        if (proj.getSender().equals(AbstractSpaceShip.getSender())) {
            return false;
        }

        for (AbstractSpaceShip ship : ships) {
            if (ship.isColliding(proj)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCollision(IPositionable obj) {
        for (AbstractSpaceShip ship : ships) {
            if (ship.isColliding(obj)) {
                return true;
            }
        }
        return false;
    }

    public int removeShipOnHit(Projectile proj, boolean collateralDamage) {
        int points = 0;
        if (proj.getSender().equals(AbstractSpaceShip.getSender())) {
            return 0;
        }

        for (int i = ships.size() - 1; i >= 0; i--) {
            if (ships.get(i).isColliding(proj)) {
                points += ships.get(i).getPoints();
                ships.remove(i);
                if (collateralDamage) {
                    break;
                }
            }
        }

        return points;
    }


    public int size() {
        return ships.size();
    }


    public List<AbstractSpaceShip> getShips() {
        return ships;
    }
}
