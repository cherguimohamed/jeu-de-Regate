package fr.ensicaen.genielogiciel.mvp.view;

public class ViewFactory {
    public ViewElement createElement(TypeViewElement type ) {
        return switch (type) {
            case BOAT -> new Boat();
            case BUOY -> new Buoy();
            case CHECKPOINT_AREA -> new CheckpointArea();
        };
    }
}
