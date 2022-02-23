package com.adiro.asmls;

import java.io.IOException;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.gui.menubar.IconMenuBar;
import com.adiro.asmls.gui.menubar.textmenu.TextMenuBar;

import com.adiro.asmls.logic.DebugFileCreator;
import com.adiro.asmls.logic.StartFilesCreator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        StartFilesCreator.generateFiles();

        StackPane stackPane = new StackPane();
        var scene = new Scene(stackPane, 1366, 768);
        scene.getStylesheets().add(getClass().getResource("styles/scrollbarstyle.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("styles/common-styles.css").toExternalForm());
        stage.setScene(scene);

        var contentView = new ContentView();
        var textMenu = new TextMenuBar(contentView, stage);
        var topMenu = new IconMenuBar(contentView, stage);
        var layout = new VBox(textMenu, topMenu, contentView);


        stackPane.getChildren().add(layout);

        stackPane.setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND2, null, null)));


        stage.setTitle("AsmLS");
        stage.show();
    }





}
