package fr.ensicaen.genielogiciel.mvp.model;

public abstract class BoatDecorator implements Boat {
    private Boat _decorator;
    public BoatDecorator(Boat boat) {
        _decorator = boat;
    }

    @Override
    public void setVelocityPolarMatrix(double[][] velocityPolarMatrix) {
        _decorator.setVelocityPolarMatrix(velocityPolarMatrix);
    }

    public boolean isVirementDeBord(double windDirection) {
        return _decorator.isVirementDeBord(windDirection);
    }

    public Boat getDecorator() {
        return _decorator;
    }

    @Override
    public double getOldDirection() {
        return _decorator.getOldDirection();
    }

    @Override
    public double getX() {
        return _decorator.getX();
    }

    @Override
    public double getY() {
        return _decorator.getY();
    }

    @Override
    public double getDirection() {
        return _decorator.getDirection();
    }

    @Override
    public void setDirection(double direction) {
        _decorator.setDirection(direction);
    }

    @Override
    public void setOldDirection(double oldDirection) {
        _decorator.setOldDirection(oldDirection);
    }
    @Override
    public void setX(double x) {
        _decorator.setX(x);
    }

    @Override
    public void setY(double y) {
        _decorator.setY(y);
    }

    @Override
    public void setDx(double dx) {
        _decorator.setDx(dx);
    }

    @Override
    public void setDy(double dy) {
        _decorator.setDy(dy);
    }

    @Override
    public double getDX() {
        return _decorator.getDX();
    }

    @Override
    public double getDy() {
        return _decorator.getDy();
    }

    @Override
    public double[][] getVelocityPolarMatrix() {
        return _decorator.getVelocityPolarMatrix();
    }

    @Override
    public double getVelocityNorm() {
        return _decorator.getVelocityNorm();
    }

    public double moveBackX(){
        return _decorator.moveBackX();
    }

    public double moveBackY(){
        return _decorator.moveBackY();
    }

    public void rotateBack() {
        _decorator.rotateBack();
    }
}
