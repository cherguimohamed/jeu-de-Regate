package fr.ensicaen.genielogiciel.mvp.presenter;

import javafx.scene.input.KeyCode;

public class RightArrowHandler extends KeyHandler{
    GamePresenter _gamePresenter;

    public RightArrowHandler(KeyHandler handler, GamePresenter gamePresenter) {
        super(handler);
        _gamePresenter = gamePresenter;
    }

    public RightArrowHandler(GamePresenter gamePresenter) {
        _gamePresenter = gamePresenter;
    }

    public void handle(KeyCode code) {
        if(code == KeyCode.RIGHT)
            _gamePresenter.rotateBoat(+2);
        else {
            super.handle(code);
        }
    }
}
