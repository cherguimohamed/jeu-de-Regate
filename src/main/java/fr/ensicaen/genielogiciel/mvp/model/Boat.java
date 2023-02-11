package fr.ensicaen.genielogiciel.mvp.model;


import static java.lang.Math.abs;
import static java.lang.Math.min;

public interface Boat extends ModelElement {
    public abstract void move(double windDirection, double windStrength);
    public abstract void rotate(double angle, double windDirection);

    public boolean isVirementDeBord(double windDirection);
    public double getOldDirection();
    public double getX();
    public double getY();
    
    public double getDirection();

    public void setDirection(double _direction);

    public void setOldDirection(double _old_direction);

    public void setX(double _x);

    public void setY(double _y);

    public void setDx(double _dx);

    public void setDy(double _dy);
    public double getDX();

    public double getDy();

    public void setVelocityPolarMatrix(double[][] velocityPolarMatrix);
    public double[][] getVelocityPolarMatrix();

    public double getVelocityNorm();

    public double moveBackX();
    public double moveBackY();
    public void rotateBack();

}
