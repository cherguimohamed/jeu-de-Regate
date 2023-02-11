package fr.ensicaen.genielogiciel.mvp.model;

public class Wind implements ModelElement{
    private double _direction, _strength;

    public Wind(double direction, double strength) {
        _direction = direction;
        _strength = strength;
    }

    public double getDirection() {
        return _direction;
    }

    public double getStrength() {
        return _strength;
    }
}
