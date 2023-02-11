package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.*;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.File;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;

public class GamePresenter {
    private GameView _view;
    private GameModel _model;
    private KeyHandler _handler;
    private Timeline _timeline;
    private boolean _started = false;
    private boolean _penalised = false;
    private double _startTime;


    public GamePresenter() {
        _handler = new SpaceHandler(new LeftArrowHandler( new RightArrowHandler(this), this), this);
    }

    public void setView(GameView view) {
        _view = view;
        _view.addKeyHandler(_handler);
    }

    public void setModel(GameModel model) {
        _model = model;
    }

    public void loadCourse(String file) {
        XMLReader xmlReader = new XMLReader(new File(this.getClass().getResource("courses/" + file).getFile()));
        try {
            _model.setWind(Double.parseDouble(xmlReader.getFirstElementAttribute("wind", "direction")), Double.parseDouble(xmlReader.getFirstElementAttribute("wind", "strength")));
            double pbX = Double.parseDouble(xmlReader.getFirstElementAttribute("playerBoat", "x"));
            double pbY = Double.parseDouble(xmlReader.getFirstElementAttribute("playerBoat", "y"));
            double pbDirection = Double.parseDouble(xmlReader.getFirstElementAttribute("playerBoat", "direction"));
            _model.initializaBoat(pbX, pbY, pbDirection);
            _view.addBoat(pbX, pbY, pbDirection);
            _view.addCoastLine(Double.parseDouble(xmlReader.getFirstElementAttribute("coastLine", "x")));
            for (int i = 1; i <= Integer.parseInt(xmlReader.getFirstElementAttribute("buoys", "nb")); i++) {
                _view.addBuoy(Double.parseDouble(xmlReader.getSpecifiedElementAttribute("buoy", "id", String.valueOf(i), "x")), Double.parseDouble(xmlReader.getSpecifiedElementAttribute("buoy", "id", String.valueOf(i), "y")));
            }
            for (int i = 1; i <= Integer.parseInt(xmlReader.getFirstElementAttribute("checkpoints", "nb")); i++) {
                _view.addCheckpoint(Double.parseDouble(xmlReader.getSpecifiedElementAttribute("checkpoint", "id", String.valueOf(i), "x")), Double.parseDouble(xmlReader.getSpecifiedElementAttribute("checkpoint", "id", String.valueOf(i), "y")), Double.parseDouble(xmlReader.getSpecifiedElementAttribute("checkpoint", "id", String.valueOf(i), "width")), Double.parseDouble(xmlReader.getSpecifiedElementAttribute("checkpoint", "id", String.valueOf(i), "height")));
            }
        } catch (Exception e) {
            errorLoadingCourse(e);
        }
    }

    public void setStart() {
        _view.nextCheckpoint();
        _view.addVelocityText();
        _view.addArrowBoatDirection(_model.getBoatDirection());
        _view.addArrowWind(_model.getWindDirection());
        _view.addVelocityWind(_model.getWindVelocity());
        _view.addTime();
        _view.addScale();
    }

    public void start() {
        if(!_started) {
            _started = true;
            _timeline = new Timeline(new KeyFrame(Duration.millis(50), onFinished -> {
                update();
                render();
            }));
            _startTime = currentTimeMillis();
            _timeline.setCycleCount(Animation.INDEFINITE);
            _timeline.play();
        }
    }

    private void update() {
        if(!_penalised)
            _model.moveBoat();
    }

    public double updateBackX() {
        return _model.moveBoatBackX();
    }

    public double updateBackY() {
        return _model.moveBoatBackY();
    }

    private void render() {
        _view.updateVelocity(abs(_model.getBoat().getVelocityNorm()));
        _view.setTime(new Duration(currentTimeMillis() - _startTime));
        _view.updateArrowBoatDirection(_model.getBoatDirection());
        _view.moveBoat(_model.getBoat().getX(), _model.getBoat().getY(), _model.getBoat().getDirection());
    }

    public void rotateBoat(double angle) {
        if(!_penalised)
            _model.rotateBoat(angle, _model.getWindDirection());
    }
    public void rotateBoatBack(){
        if(!_penalised)
            _model.rotateBoatBack();
    }

    private void errorLoadingCourse(Exception e) {
        System.exit(0);
    }

    public void loadBoat(String file) {
        _model.setVelocityPolarMatrix(file);
    }

    public void end() {
        _timeline.stop();
    }

    public void penalise() {
        _penalised = true;
        Timeline penalisation = new Timeline(new KeyFrame(Duration.seconds(1), onFinished -> {
            _penalised = false;
        }));
        penalisation.setCycleCount(1);
        penalisation.play();
    }

    public void configBoat(int nbCrew, boolean sailIsNormal) {
        Boat boat;
        if(nbCrew == 4 && sailIsNormal) {
            boat = new FourCrewmate(new UsualSail(new BoatModel()));
        }
        else if(nbCrew == 2 && sailIsNormal) {
            boat = new TwoCrewmate(new UsualSail(new BoatModel()));
        }
        else if(nbCrew == 4 && !sailIsNormal) {
            boat = new FourCrewmate(new BigSail(new BoatModel()));
        }
        else {
            boat = new TwoCrewmate(new BigSail(new BoatModel()));
        }
        _model.setBoat(boat);
    }
}
