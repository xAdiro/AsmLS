package com.adiro.asmls.gui.menubar.textmenu;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.content.ContentView;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

public class TextMenuBar extends MenuBar{

    public TextMenuBar(ContentView contentView, Stage stage) {
        super();

        setBackground(
                new Background(
                        new BackgroundFill(
                                GuiColors.BACKGROUND2,null,null)));

        Menu menuFile = new Menu();
        Label label = new Label("File");
        label.setTextFill(GuiColors.TEXT);
        menuFile.setGraphic(label);
        menuFile.getItems().add(new FileSubMenu(contentView, stage));

        Menu menuEdit = new Menu();
        Label label2 = new Label("Edit");
        label2.setTextFill(GuiColors.TEXT);
        menuEdit.setGraphic(label2);

        var settingsMenu = new SettingsMenu(stage.getScene().getRoot());

        getMenus().addAll(menuFile, menuEdit, settingsMenu);
    }

}

