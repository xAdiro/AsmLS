package com.adiro.nasm_ide;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



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
    	
    	var fileButton = new changeFileButton(reader, stage);
    	var sourceButton = new SourceButton(sourceCode, stage);
    	
    	
    	var column1 = new VBox(sourceCode, nextButton, fileButton, sourceButton);
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