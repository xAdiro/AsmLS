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
    private ContentView contentView;
    @FXML
    private MoveToStartButton moveToStartButton;
    @FXML
    private MoveManyBackButton moveManyBackButton;
    @FXML
    private MoveBackButton moveBackButton;
    @FXML
    private MoveNextButton moveNextButton;
    @FXML
    private MoveManyNextButton moveManyNextButton;
    @FXML
    private MoveToEndButton moveToEndButton;
    @FXML
    private RefreshButton refreshButton;
    @FXML
    private FileSubMenu fileSubMenu;
    @FXML
    private VBox mainBox;
    @FXML
    private Stage stage;

    public FxmlController(){


    }

    @FXML
    public void initialize(){
        mainBox = new VBox();
        //Platform.runLater(this::loadStage);
        contentView = new ContentView();
//        fileSubMenu = new FileSubMenu(contentView, stage);
//
//        moveToStartButton = new MoveToStartButton(contentView);
//        moveManyBackButton = new MoveManyBackButton(contentView);
//        moveBackButton = new MoveBackButton(contentView);
//        moveNextButton = new MoveNextButton(contentView);
        //moveManyNextButton = new MoveManyNextButton(contentView);
//        moveToEndButton = new MoveToEndButton(contentView);
//        refreshButton = new RefreshButton(contentView);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}