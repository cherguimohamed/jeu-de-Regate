package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.Main;
import fr.ensicaen.genielogiciel.mvp.presenter.SetupPresenter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


import java.util.ResourceBundle;


public class SetupView {
    private SetupPresenter _presenter;
    private Stage _stage;
    @FXML
    private RadioButton _sailUsual, _crewTwo;
    @FXML
    private ToggleGroup _sailGroup, _crewGroup;
    @FXML
    private ChoiceBox _choiceCourse, _choiceBoat;
    @FXML
    private Label _errorMessage;

    public static ResourceBundle getMessageBundle() {
        return Main.getMessageBundle();
    }

    public void setPresenter( SetupPresenter presenter ) {
        _presenter = presenter;
    }

    public void setCourseList(ObservableList courseList) {
        _choiceCourse.setItems(courseList);
        _choiceCourse.setValue(courseList.get(courseList.size()-1));
    }

    public void setBoatList(ObservableList boatList) {
        _choiceBoat.setItems(boatList);
        _choiceBoat.setValue(boatList.get(boatList.size()-1));
    }

    public void show() {
        _stage.show();
    }

    public void close() {
        _stage.close();
    }

    public void printError(String error) {
        _errorMessage.setText(error);
    }

    @FXML
    private void initialize() {
        _sailGroup.selectToggle(_sailUsual);
        _crewGroup.selectToggle(_crewTwo);
    }

    public void onClickOnStartGame(ActionEvent actionEvent) {
        _presenter.launchGame(_choiceCourse.getValue().toString(), _choiceBoat.getValue().toString(), _crewTwo.isSelected() ? 2 : 4, _sailUsual.isSelected());
    }

    public static class SetupViewFactory {
        private SetupViewFactory() {
            // Factory class as Utility class where the constructor is private
        }

        public static SetupView createView( Stage primaryStage ) throws IOException {
            FXMLLoader loader = new FXMLLoader(SetupView.class.getResource("SetupView.fxml"), Main.getMessageBundle());
            Parent root = loader.load();
            SetupView view = loader.getController();
            Scene scene = new Scene(root);
            view._stage = primaryStage;
            primaryStage.setScene(scene);
            return view;
        }
    }
}