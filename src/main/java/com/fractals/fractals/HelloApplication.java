package com.fractals.fractals;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("0.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 500);

        stage.setTitle("КГ лаб1 Терагоны");
        stage.setScene(scene);

        stage.resizableProperty().set(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}