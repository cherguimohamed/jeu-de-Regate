package fr.ensicaen.genielogiciel.mvp.model;

public class BoatModel implements Boat{
    private double _x, _y, _dx, _dy, _direction, _oldDirection;
    private double[][] _velocyPolarMatrix;

    public BoatModel() {}

    @Override
    public void move(double windDirection, double windStrength) {

    }

    @Override
    public void rotate(double angle, double windDirection) {
    }

    @Override
    public void setVelocityPolarMatrix(double[][] velocityPolarMatrix) {
        _velocyPolarMatrix = velocityPolarMatrix;
    }

    @Override
    public double[][] getVelocityPolarMatrix() {
        return _velocyPolarMatrix;
    }

    @Override
    public double getVelocityNorm() {
        return Math.sqrt(_dx*_dx + _dy*_dy);
    }

    @Override
    public boolean isVirementDeBord(double windDirection) {
        if(( _oldDirection> (180 + windDirection) && _oldDirection < 360 ) || (_oldDirection > 0 && _oldDirection < windDirection)) {
            if(_direction > windDirection && _direction < 180 + windDirection){
                return true;
            }
        }
        return false;
    }

    @Override
    public double getOldDirection() {
        return _oldDirection;
    }

    @Override
    public double getX() {
        return _x;
    }

    @Override
    public double getY() {
        return _y;
    }

    @Override
    public double getDirection() {
        return _direction;
    }

    @Override
    public void setDirection(double direction) {
        _direction = direction;
    }

    @Override
    public void setOldDirection(double oldDirection) {
        _oldDirection = oldDirection;
    }

    @Override
    public void setX(double x) {
        _x =  x;
    }

    @Override
    public void setY(double y) {
        _y = y;
    }

    @Override
    public void setDx(double dx) {
        _dx = dx;
    }

    @Override
    public void setDy(double dy) {
        _dy = dy;
    }

    @Override
    public double getDX() {
        return _dx;
    }

    @Override
    public double getDy() {
        return _dy;
    }

    public double moveBackX(){
        _x = this.getX() - 10*this.getDX();
        return getX();
    }

    public double moveBackY(){
        _y = this.getY()- 10*this.getDy();
        return getY();
    }

    public void rotateBack() {
        double angle = _direction;
        if (angle > 70 && angle<110)
            _direction = ( _direction + 90) % 360;
    }
}
