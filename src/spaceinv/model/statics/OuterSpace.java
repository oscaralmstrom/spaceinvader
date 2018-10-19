package spaceinv.model.statics;

import spaceinv.model.IPositionable;

import static spaceinv.model.SpaceInv.GAME_WIDTH;

/*
    Used to check if rockets from gun have vanished
 */
public class OuterSpace implements IPositionable {

    public static final double OUTERSPACE_HEIGHT = 5;
    public static final double width = GAME_WIDTH;
    public static final double X = 0, Y = -50;

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
        return OUTERSPACE_HEIGHT;
    }
}
