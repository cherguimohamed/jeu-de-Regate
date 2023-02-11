package fr.ensicaen.genielogiciel.mvp.model;

public class FourCrewmate extends BoatDecorator {
    private final double TIMEDECREASE = 0.1;
    public FourCrewmate(Boat b) {
        super(b);
    }

    @Override
    public void move(double windDirection, double windStrength) {
        super.getDecorator().move(windDirection, windStrength);
    }

    @Override
    public void rotate(double angle, double windDirection) {
        super.getDecorator().setOldDirection(super.getDecorator().getDirection());
        if (super.isVirementDeBord(windDirection)) {
            super.getDecorator().setDirection((360 + super.getDecorator().getDirection() + (angle * TIMEDECREASE)) % 360);

        } else {
            super.getDecorator().setDirection((360 + super.getDecorator().getDirection() + angle) % 360);
        }
    }
}
