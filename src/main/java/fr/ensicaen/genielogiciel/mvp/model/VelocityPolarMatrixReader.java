package fr.ensicaen.genielogiciel.mvp.model;

import javafx.scene.SubScene;

import java.io.File;
import java.util.Scanner;

public class VelocityPolarMatrixReader {
    private Scanner _scan;

    public VelocityPolarMatrixReader(File file) {
        try {
            _scan = new Scanner(file);
        }
        catch (Exception e) {

        }
    }

    public double[][] loadVelocityPolarMatrix() {
        double[][] polarVelocityMatrix = new double[ConfigModel.SIZE_ANGLE_MATRIX][ConfigModel.SIZE_WIND_MATRIX];
        String line = "";
        polarVelocityMatrix[0][0] = 0;
        int row = 0;

        while (_scan.hasNextLine()) {
            line = _scan.nextLine();
            String[] split = line.split("\t");
            if (row == 0) {
                for (int i = 1; i < ConfigModel.SIZE_WIND_MATRIX; i++) {
                    polarVelocityMatrix[row][i] = Double.parseDouble(split[i]);
                }
            } else {
                for (int i = 0; i < ConfigModel.SIZE_WIND_MATRIX; i++) {
                        polarVelocityMatrix[row][i] = Double.parseDouble(split[i]);
                }
            }
            row++;
        }
        return polarVelocityMatrix;
    }
}
