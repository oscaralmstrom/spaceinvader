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

    public void setPosision(double _posX, double _posY){
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
}
