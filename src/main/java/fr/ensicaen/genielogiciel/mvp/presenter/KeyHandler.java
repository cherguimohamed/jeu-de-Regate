package fr.ensicaen.genielogiciel.mvp.presenter;

import javafx.scene.input.KeyCode;

import java.util.List;

public abstract class KeyHandler {
    private KeyHandler _nextHandler;

    public KeyHandler(KeyHandler handler) {
        _nextHandler = handler;
    }

    public KeyHandler() {
        _nextHandler = null;
    }

    public void handle(KeyCode code) {
        if (_nextHandler != null) {
            _nextHandler.handle(code);
        }
    }
}
