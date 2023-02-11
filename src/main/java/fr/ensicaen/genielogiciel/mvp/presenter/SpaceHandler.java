package fr.ensicaen.genielogiciel.mvp.presenter;

import javafx.scene.input.KeyCode;

public class SpaceHandler extends KeyHandler{
    private GamePresenter _gamePresenter;

    public SpaceHandler(KeyHandler handler, GamePresenter gamePresenter) {
        super(handler);
        _gamePresenter = gamePresenter;
    }

    public SpaceHandler(GamePresenter gamePresenter) {
        super();
        _gamePresenter = gamePresenter;
    }

    public void handle(KeyCode code) {
        if(code == KeyCode.SPACE) {
            _gamePresenter.start();
        }
        else {
            super.handle(code);
        }
    }
}
