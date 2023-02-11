package fr.ensicaen.genielogiciel.mvp.view;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ConfigView {
    //BUOY
    public static int BuoyRadius = 10;

    //CHECKPOINT
    public static Paint NextCheckpointColor = Color.YELLOW;
    public static double NextCheckpointOpacity = 0.25;
    public static Paint CheckpointColor = Color.YELLOW;
    public static Paint StartCheckpointColor = Color.GREEN;
    public static Paint EndCheckpointColor = Color.RED;
    public static double CheckpointOpacity = 0;

    //BOAT
    public static double boatRadiusX = 7.5;
    public static double boatRadiusY = 15;
    
    //VELOCITY
    public static double velocityX = 150;
    public static double velocityTextY = 80;

    //BOAT DIRECTION
    public static double boatDirectionX = 150;
    public static double boatDirectionStartY = 20;
    public static double boatDirectionEndY = 70;
    public static double boatDirectionTextNameY = 15;

    //WIND
    public static double windX = 50;
    public static double windStartY = 20;
    public static double windEndY = 70;
    public static double windTextNameY = 15;
    public static double windText = 80;

    //DURATION
    public static double durationY = 50;
    public static double durationX = 200;

}
