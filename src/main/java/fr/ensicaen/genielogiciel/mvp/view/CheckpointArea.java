package fr.ensicaen.genielogiciel.mvp.view;

import javafx.scene.shape.Rectangle;

public class CheckpointArea extends Rectangle implements StaticViewElement {
    public CheckpointArea() {
        super();
        setOpacity(ConfigView.CheckpointOpacity);
        setFill(ConfigView.NextCheckpointColor);
    }

    public void config(double x, double y, double width, double height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public void setNextCheckpoint() {
        setOpacity(ConfigView.NextCheckpointOpacity);
        setFill(ConfigView.NextCheckpointColor);
    }

    public void setBasicCheckpoint() {
        setOpacity(ConfigView.CheckpointOpacity);
        setFill(ConfigView.CheckpointColor);
    }

    public void setStartCheckpoint() {
        setOpacity(ConfigView.NextCheckpointOpacity);
        setFill(ConfigView.StartCheckpointColor);
    }

    public void setEndCheckpoint() {
        setOpacity(ConfigView.NextCheckpointOpacity);
        setFill(ConfigView.EndCheckpointColor);
    }
}
