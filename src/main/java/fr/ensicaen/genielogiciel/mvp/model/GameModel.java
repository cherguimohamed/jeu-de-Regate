package fr.ensicaen.genielogiciel.mvp.model;

import fr.ensicaen.genielogiciel.mvp.presenter.XMLReader;
import javafx.scene.SubScene;

import java.io.File;

public class GameModel {
    private Wind _wind;
    private Boat _boat;

    public void setWind(double direction, double strength) {
        _wind = new Wind(direction, strength);
    }

    public void setBoat(Boat boat) {
        _boat = boat;
    }

    public void initializaBoat(double x, double y, double direction) {
        _boat.setX(x);
        _boat.setY(y);
        _boat.setDirection(direction);
    }

    public void moveBoat() {
        _boat.move(_wind.getDirection(), _wind.getStrength());
    }

    public double moveBoatBackX(){
        return _boat.moveBackX();
        //System.out.println("moveBoatBack");
    }
    public double moveBoatBackY(){
        return _boat.moveBackY();
        //System.out.println("moveBoatBack");
    }
    public Boat getBoat() {
        return _boat;
    }

    public void setVelocityPolarMatrix(String file) {
        VelocityPolarMatrixReader velocityPolarMatrixReader = new VelocityPolarMatrixReader(new File(this.getClass().getResource("boats/" + file).getFile()));
        _boat.setVelocityPolarMatrix(velocityPolarMatrixReader.loadVelocityPolarMatrix());
    }

    public void rotateBoat(double angle, double windDirection) {
        _boat.rotate(angle, windDirection);
    }
    public void rotateBoatBack() {
        _boat.rotateBack();
    }

    public double getWindDirection() {
        return _wind.getDirection();
    }

    public double getWindVelocity() {
        return _wind.getStrength();
    }

    public double getBoatDirection() {
        return _boat.getDirection();
    }
}