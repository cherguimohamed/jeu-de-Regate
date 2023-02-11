package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GameView {
    private static Stage _stage;
    @FXML
    private GamePresenter _presenter;
    private Boat _playerBoat;
    private List<CheckpointArea> _checkpoints = new ArrayList<>();
    private int _actualCheckpoint = -1;
    private List<Buoy> _buoys = new ArrayList<>();
    private static ViewFactory _viewFactory;
    private Text _velocity;
    private Text _time = new Text(ConfigView.durationX, ConfigView.durationY, "0 s");
    private Arrow _direction;
    private Line _coastLine;
    @FXML
    private AnchorPane _base;


    public void setPresenter(GamePresenter presenter) {
        _presenter = presenter;
    }

    public void show() {
        _stage.show();
    }

    public void addKeyHandler(KeyHandler handler) {
        _stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            handler.handle(event.getCode());
        });
    }

    public void addBuoy(double x, double y) {
        Buoy buoy = (Buoy) _viewFactory.createElement(TypeViewElement.BUOY);
        buoy.config(x, y);
        _base.getChildren().add(buoy);
        _buoys.add(buoy);
    }

    public void addCheckpoint(double x, double y, double width, double height) {
        CheckpointArea checkpointArea = (CheckpointArea) _viewFactory.createElement(TypeViewElement.CHECKPOINT_AREA);
        checkpointArea.config(x, y, width, height);
        _base.getChildren().add(checkpointArea);
        _checkpoints.add(checkpointArea);
    }

    public void addBoat(double x, double y, double direction) {
        _playerBoat = (Boat) _viewFactory.createElement(TypeViewElement.BOAT);
        _playerBoat.config(x, y, direction);
        _base.getChildren().add(_playerBoat);
    }

    public void nextCheckpoint() {
        if(_actualCheckpoint >= 0)
            _checkpoints.get(_actualCheckpoint).setBasicCheckpoint();
        _actualCheckpoint += _actualCheckpoint < _checkpoints.size() - 1 ? 1 : 0;
        if(_actualCheckpoint == _checkpoints.size() - 1)
            _checkpoints.get(_actualCheckpoint).setEndCheckpoint();
        else
            _checkpoints.get(_actualCheckpoint).setNextCheckpoint();
    }

    public void addArrowWind(double direction) {
        Text wind = new Text(Main.getMessageBundle().getString("view.wind.text"));
        wind.setX(ConfigView.windX - wind.getLayoutBounds().getWidth()/2);
        wind.setY(ConfigView.windTextNameY);
        _base.getChildren().add(wind);
        Arrow arrowWind = new Arrow();
        arrowWind.setStartX(ConfigView.windX);
        arrowWind.setEndX(ConfigView.windX);
        arrowWind.setStartY(ConfigView.windStartY);
        arrowWind.setEndY(ConfigView.windEndY);
        arrowWind.setRotate(180+direction);
        _base.getChildren().add(arrowWind);
    }

    public void addVelocityWind(double velocity) {
        Text velocityWind = new Text(velocity + " " + Main.getMessageBundle().getString("view.velocity.text"));
        velocityWind.setX(ConfigView.windX - velocityWind.getLayoutBounds().getWidth()/2);
        velocityWind.setY(ConfigView.windText);
        _base.getChildren().add(velocityWind);
    }

    public void moveBoat(double x, double y, double direction) {
        if(boatTouchingBuoy(_playerBoat)) {
            //System.out.println("Un collision");
            //_presenter.rotateBoatBack();
            //_playerBoat.set(90);
            _playerBoat.setCenterX(_presenter.updateBackX());
            _playerBoat.setCenterY(_presenter.updateBackY());

            //_presenter.updateBackX();
            _presenter.penalise();
        }
        else if(boatOutOfMap(_playerBoat)) {
            _presenter.rotateBoat(180);
            _playerBoat.setRotate(180);
            _presenter.penalise();
        }
        else if(boatTouchingCoastLine(_playerBoat)) {
            _presenter.end();
        }
        else
            _playerBoat.config(x, y, direction);
        if(boatInCheckpoint()) {
            nextCheckpoint();
        }
        if(boatInLastCheckpoint()){
            _presenter.end();
        }
    }

    public boolean boatOutOfMap(Boat boat) {
        return boat.getCenterX() - boat.getRadiusX() < 0 || boat.getCenterX() + boat.getRadiusX() < 0 || boat.getCenterX() + boat.getRadiusX() > _base.getWidth()  || boat.getCenterX() - boat.getRadiusX() > _base.getWidth() || boat.getCenterY() + boat.getRadiusY() < 0 || boat.getCenterY() - boat.getRadiusY() < 0 || boat.getCenterY() + boat.getRadiusY()> _base.getHeight() || boat.getCenterY() - boat.getRadiusY() > _base.getHeight();
    }

    public boolean boatTouchingBuoy(Boat boat) {
        for(Buoy buoy : _buoys) {
            if(buoy.getBoundsInParent().intersects(boat.getBoundsInParent()))
                return true;
        }
        return false;
    }

    public boolean boatTouchingCoastLine(Boat boat) {
        return boat.getBoundsInParent().intersects(_coastLine.getBoundsInParent());
    }

    public void addArrowBoatDirection(double direction) {
        Text boat  = new Text(Main.getMessageBundle().getString("view.boat.text"));
        boat.setX(ConfigView.boatDirectionX - boat.getLayoutBounds().getWidth()/2);
        boat.setY(ConfigView.boatDirectionTextNameY);
        _base.getChildren().add(boat);
        _direction = new Arrow();
        _direction.setStartX(ConfigView.boatDirectionX);
        _direction.setEndX(ConfigView.boatDirectionX);
        _direction.setStartY(ConfigView.boatDirectionStartY);
        _direction.setEndY(ConfigView.boatDirectionEndY);
        _direction.setRotate(180+direction);
        _base.getChildren().add(_direction);
    }

    public void updateArrowBoatDirection(double direction) {
        _direction.setRotate(180+direction);
    }

    public void addVelocityText() {
        _velocity = new Text(0 + " " + Main.getMessageBundle().getString("view.velocity.text"));
        _velocity.setX(ConfigView.velocityX - _velocity.getLayoutBounds().getWidth()/2);
        _velocity.setY(ConfigView.velocityTextY);
        _base.getChildren().add(_velocity);
    }

    public void updateVelocity(double velocity) {
        _velocity.setText(new DecimalFormat("#.##").format(velocity) + " " + Main.getMessageBundle().getString("view.velocity.text"));
    }

    private boolean boatInLastCheckpoint() {
        return _actualCheckpoint == _checkpoints.size()-1 && _playerBoat.getCenterX() >= _checkpoints.get(_checkpoints.size()-1).getX() && _playerBoat.getCenterX() <= _checkpoints.get(_checkpoints.size()-1).getX() + _checkpoints.get(_checkpoints.size()-1).getWidth() && _playerBoat.getCenterY() >= _checkpoints.get(_checkpoints.size()-1).getY() && _playerBoat.getCenterY() <= _checkpoints.get(_checkpoints.size()-1).getY() + _checkpoints.get(_checkpoints.size()-1).getHeight();
    }

    public boolean boatInCheckpoint() {
        return _playerBoat.getCenterX() >= _checkpoints.get(_actualCheckpoint).getX() && _playerBoat.getCenterX() <= _checkpoints.get(_actualCheckpoint).getX() + _checkpoints.get(_actualCheckpoint).getWidth() && _playerBoat.getCenterY() >= _checkpoints.get(_actualCheckpoint).getY() && _playerBoat.getCenterY() <= _checkpoints.get(_actualCheckpoint).getY() + _checkpoints.get(_actualCheckpoint).getHeight();
    }

    public void setTime(Duration duration) {
        _time.setText(new DecimalFormat("#.#").format(duration.toSeconds()) + " s");
    }

    public void addTime() {
        _base.getChildren().add(_time);
    }

    public void addCoastLine(double x) {
        _coastLine = new Line(x, 0, x, _base.getHeight());
        _base.getChildren().add(_coastLine);
    }

    public void addScale() {
        Line scale = new Line(750,10,750,60);
        _base.getChildren().add(scale);
        Text scaleText = new Text(755,30,"6.3 m");
        _base.getChildren().add(scaleText);

    }

    public static class GameViewFactory {
        private GameViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static GameView createView() throws IOException {
            FXMLLoader loader = new FXMLLoader(GameView.class.getResource("SpotMap.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            GameView view = loader.getController();
            Stage stage = new Stage();
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle(Main.getMessageBundle().getString("project.title"));
            stage.setScene(scene);
            _stage = stage;
            _viewFactory = new ViewFactory();
            _stage.setResizable(false);

            return view;
        }
    }


}