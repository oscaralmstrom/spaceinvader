package spaceinv.model.ships;


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
    private final static double chanseOfShooting = 0.01;

    private static final Random rand = new Random();
    private final List<AbstractSpaceShip> ships;
    private Direction moveDir;

    public ShipFormation(List<AbstractSpaceShip> ships) {
        this.ships = ships;
        DOWN_STEP = ships.get(0).getHeight();
        moveDir = Direction.RIGHT;
    }

    // TODO Some method to move the ships
    public void move(double moveSpeed) {

        switch (moveDir) {
            case RIGHT:
                moveSpeed = Math.abs(moveSpeed);

                if (movesOutOfWindow(moveSpeed)) {
                    stepDown();
                    moveDir = Direction.LEFT;
                } else {
                    moveAllX(moveSpeed);
                }
                break;
            case LEFT:
                moveSpeed = -Math.abs(moveSpeed);

                if (movesOutOfWindow(moveSpeed)) {
                    stepDown();
                    moveDir = Direction.RIGHT;
                } else {
                    moveAllX(moveSpeed);
                }
                break;
        }

    }

    public Projectile spawnInvaderProjectiles() {
        if (rand.nextDouble() < chanseOfShooting) {
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

    public int removeShipOnHit(Projectile proj) {
        int points = 0;
        if (proj.getSender().equals(AbstractSpaceShip.getSender())) {
            return 0;
        }

        for (int i = ships.size() - 1; i >= 0; i--) {
            if (ships.get(i).isColliding(proj)) {
                points += ships.get(i).getPoints();
                ships.remove(i);
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
