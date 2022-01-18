package com.adiro.nasm_ide;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.nio.file.Files;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

    	var	sourceCode = new SourceViewArea();
    	var registers = new RegistersView();
    	var	reader = new LogReader(registers, sourceCode);
    	var nextButton = new MoveNextButton(sourceCode, reader);
    	
    	
    	var column1 = new VBox(sourceCode, nextButton);
    	var column2 = new VBox(registers);
    	
    	var layout = new HBox(column1, column2);

        var scene = new Scene(new StackPane(layout), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}