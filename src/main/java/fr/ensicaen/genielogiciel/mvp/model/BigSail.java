package fr.ensicaen.genielogiciel.mvp.model;

import static fr.ensicaen.genielogiciel.mvp.model.ConfigModel.SIZE_ANGLE_MATRIX;
import static fr.ensicaen.genielogiciel.mvp.model.ConfigModel.SIZE_WIND_MATRIX;
import static java.lang.Math.abs;

public class BigSail extends BoatDecorator {
    private final double SLOWDOWN = 0.5;
    public BigSail(Boat b) {
        super(b);
    }

    public void move(double windDirection, double windStrength) {
        double angle = abs(windDirection - super.getDirection()%180);

        double minDiffVelocity = abs(windStrength - super.getVelocityPolarMatrix()[0][1]);
        int col = 0;
        for (int count = 2; count < SIZE_WIND_MATRIX; count++) {
            if (abs(windStrength - super.getVelocityPolarMatrix()[0][count]) < minDiffVelocity) {
                col = count;
                minDiffVelocity = abs(angle - super.getVelocityPolarMatrix()[0][count]);
            }
        }

        double minDiffAngle = abs(angle - super.getVelocityPolarMatrix()[1][1]);
        int row = 1;
        for (int count = 2; count < SIZE_ANGLE_MATRIX; count++) {
            if (abs(angle - super.getVelocityPolarMatrix()[count][0]) < minDiffAngle) {
                minDiffAngle = abs(angle - super.getVelocityPolarMatrix()[count][0]);
                row = count;
            }
        }

        System.out.println(super.getVelocityPolarMatrix()[row][col]);
        if(super.isVirementDeBord(windDirection)) {
            super.setDy(super.getVelocityPolarMatrix()[row][col] * -Math.cos(super.getDirection() * Math.PI / 180) * 0.1 * SLOWDOWN);
            super.setDx(super.getVelocityPolarMatrix()[row][col] * Math.sin(super.getDirection() * Math.PI / 180) * 0.1 * SLOWDOWN );
        }
        else {
            super.setDy(super.getVelocityPolarMatrix()[row][col] * -Math.cos(super.getDirection() * Math.PI / 180) * 0.1);
            super.setDx(super.getVelocityPolarMatrix()[row][col] * Math.sin(super.getDirection() * Math.PI / 180) * 0.1);

        }
        super.setX(super.getDX()+super.getX());
        super.setY(super.getDy()+super.getY());
    }

    @Override
    public void rotate(double angle, double windDirection) {
        super.getDecorator().rotate(angle, windDirection);
    }
}
