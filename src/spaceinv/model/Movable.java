package spaceinv.model;

public abstract class Movable implements IPositionable {

    private double posY = 0, posX = 0;
    private double speedX, speedY;

    public Movable(double _posX, double _posY) {
        posX = _posX;
        posY = _posY;
        speedX = 0;
        speedY = 0;
    }

    public void setSpeed(double _speedX, double _speedY) {
        speedX = _speedX;
        speedY = _speedY;
    }

    public void setPosition(double _posX, double _posY){
        posX = _posX;
        posY = _posY;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void move() {
        posY += speedY;
        posX += speedX;
    }

    @Override
    public double getX() {
        return posX;
    }

    @Override
    public double getY() {
        return posY;
    }

    public boolean isColliding(IPositionable obj2) {
        if (getX() + getWidth() < obj2.getX() || obj2.getX() + obj2.getWidth() < getX()) {
            return false;
        }

        if (getY() + getHeight() < obj2.getY() || obj2.getY() + obj2.getHeight() < getY()) {
            return false;
        }

        return true;
    }
}
