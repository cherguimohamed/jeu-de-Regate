package fr.ensicaen.genielogiciel.mvp;

import fr.ensicaen.genielogiciel.mvp.model.GameModel;
import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.SetupPresenter;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import fr.ensicaen.genielogiciel.mvp.view.SetupView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;

public final class Main extends Application {

    public static void main( String[] args ) {
        launch(args);
    }

    public static ResourceBundle getMessageBundle() {
        return ResourceBundle.getBundle("fr.ensicaen.genielogiciel.mvp.MessageBundle");
    }

    @Override
    public void start( final Stage primaryStage ) throws Exception {
        primaryStage.setTitle(getMessageBundle().getString("project.title"));

        SetupView view = SetupView.SetupViewFactory.createView(primaryStage);
        SetupPresenter presenter = new SetupPresenter();

        presenter.setView(view);
        view.setPresenter(presenter);
        presenter.makeView();
        view.show();
    }

    @Override
    public void stop() {
        System.out.println(getMessageBundle().getString("project.bye"));
    }
}