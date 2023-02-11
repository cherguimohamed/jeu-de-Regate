package fr.ensicaen.genielogiciel.mvp.presenter;

import javafx.scene.input.KeyCode;

public class LeftArrowHandler extends KeyHandler{
    private GamePresenter _gamePresenter;
    public LeftArrowHandler(KeyHandler handler, GamePresenter gamePresenter) {
        super(handler);
        _gamePresenter = gamePresenter;
    }

    public LeftArrowHandler(GamePresenter gamePresenter) {
        super();
        _gamePresenter = gamePresenter;
    }

    public void handle(KeyCode code) {
        if(code == KeyCode.LEFT)
            _gamePresenter.rotateBoat(-2);
        else {
            super.handle(code);
        }
    }
}
