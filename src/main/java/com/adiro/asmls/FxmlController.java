package com.adiro.asmls;

import com.adiro.asmls.gui.button.*;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.gui.content.RegistersView;
import com.adiro.asmls.gui.menubar.textmenu.FileSubMenu;
import com.adiro.asmls.logic.LogReader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FxmlController {

    @FXML
    ContentView contentView;
    @FXML
    MoveToStartButton moveToStartButton;
    @FXML
    MoveManyBackButton moveManyBackButton;
    @FXML
    MoveBackButton moveBackButton;
    @FXML
    MoveNextButton moveNextButton;
    @FXML
    MoveManyNextButton moveManyNextButton;
    @FXML
    MoveToEndButton moveToEndButton;
    @FXML
    RefreshButton refreshButton;
    @FXML
    FileSubMenu fileSubMenu;
    @FXML
    VBox mainBox;
    private Stage stage;

    public FxmlController(){


    }

    @FXML
    public void initialize(){
        mainBox = new VBox();
        //Platform.runLater(this::loadStage);
        contentView = new ContentView();
        fileSubMenu = new FileSubMenu(contentView, stage);

        moveToStartButton = new MoveToStartButton(contentView);
        moveManyBackButton = new MoveManyBackButton(contentView);
        moveBackButton = new MoveBackButton(contentView);
        moveNextButton = new MoveNextButton(contentView);
        moveManyNextButton = new MoveManyNextButton(contentView);
        moveToEndButton = new MoveToEndButton(contentView);
        refreshButton = new RefreshButton(contentView);
    }

//    private void loadStage(){
//        stage = (Stage)mainBox.getScene().getWindow();
//    }
}