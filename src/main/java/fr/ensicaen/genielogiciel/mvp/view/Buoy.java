package fr.ensicaen.genielogiciel.mvp.view;

import javafx.scene.shape.Circle;

public class Buoy extends Circle implements StaticViewElement {
    public Buoy() {
        super(0, 0, ConfigView.BuoyRadius);
    }

    public void config(double x, double y) {
        setCenterX(x);
        setCenterY(y);
    }
}
