package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.model.GameModel;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import fr.ensicaen.genielogiciel.mvp.view.SetupView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SetupPresenter {
    private SetupView _view;
    public void launchGame(String course, String boat, int nbCrew, boolean normal) {
        try {
        _view.close();
        GameView view = GameView.GameViewFactory.createView();
        GamePresenter presenter = new GamePresenter();

        presenter.setView(view);
        presenter.setModel(new GameModel());
        view.setPresenter(presenter);

        presenter.configBoat(nbCrew, normal);
        presenter.loadCourse(course);
        presenter.loadBoat(boat);
        presenter.setStart();
        view.show();
        }
        catch (IOException e) {
            _view.printError("Erreur");
        }
    }

    public void setView(SetupView view) {
        _view = view;
    }

    public ObservableList getCoursesList() {
        List names = new ArrayList();
        for(File file : new File(this.getClass().getResource("courses").getPath()).listFiles()) {
            names.add(file.getName());
        }
        return FXCollections.observableArrayList(names);
    }

    public ObservableList getBoatList() {
        List names = new ArrayList();
        for(File file : new File(GameModel.class.getResource("boats").getPath()).listFiles()) {
            names.add(file.getName());
        }
        return FXCollections.observableArrayList(names);
    }

    public void makeView() {
        _view.setBoatList(getBoatList());
        _view.setCourseList(getCoursesList());
    }
}
