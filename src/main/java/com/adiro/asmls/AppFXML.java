package com.adiro.asmls;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppFXML extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppFXML.class.getResource("fxml/index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1366, 768);
        scene.getStylesheets().add(getClass().getResource("fxml/style.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("styles/scrollbarstyle.css").toExternalForm());
        stage.setTitle("AsmLS");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}