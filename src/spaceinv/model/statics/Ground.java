package spaceinv.model.statics;


import spaceinv.model.IPositionable;

import static spaceinv.model.SpaceInv.GAME_HEIGHT;
import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Used to check if projectiles from ships have hit the ground

 */
public class Ground implements IPositionable {

    private static double width = GAME_WIDTH;
    private static double height = GAME_WIDTH / 40;
    private static double X, Y;

    public Ground() {
        X = 0;
        Y = GAME_HEIGHT - height;
    }

    @Override
    public double getX() {
        return X;
    }

    @Override
    public double getY() {
        return Y;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }
}
