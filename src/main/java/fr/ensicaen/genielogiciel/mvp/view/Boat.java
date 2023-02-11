package fr.ensicaen.genielogiciel.mvp.view;

import javafx.scene.shape.Ellipse;

public class Boat extends Ellipse implements MovableViewElement {
    public Boat() {
        super();
        setRadiusX(ConfigView.boatRadiusX);
        setRadiusY(ConfigView.boatRadiusY);
    }

    public void config(double x, double y, double direction) {
        setCenterX(x);
        setCenterY(y);
        setRotate(direction);
    }

    @Override
    public void move(double x, double y) {

    }
}
