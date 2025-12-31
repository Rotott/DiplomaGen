package org.example.diploma.ui.desktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                DesktopApp.class.getResource("/fxml/diploma-view.fxml")
        );
        Scene scene = new Scene(loader.load());
        stage.setTitle("Diploma Generator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
