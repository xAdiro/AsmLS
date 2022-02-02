package com.adiro.nasm_ide;

import java.io.IOException;
import com.adiro.nasm_ide.gui.content.ContentView;
import com.adiro.nasm_ide.gui.menubar.IconMenuBar;
import com.adiro.nasm_ide.gui.menubar.textmenu.TextMenuBar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

    	var contentView = new ContentView();    	
    	var textMenu = new TextMenuBar(contentView, stage);
    	var topMenu = new IconMenuBar(contentView, stage);
    	var layout = new VBox(textMenu, topMenu, contentView);

        var scene = new Scene(new StackPane(layout), 1366, 768);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}