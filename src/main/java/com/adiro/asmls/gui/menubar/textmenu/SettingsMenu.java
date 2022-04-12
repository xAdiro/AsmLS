package com.adiro.asmls.gui.menubar.textmenu;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;

import com.adiro.asmls.gui.window.settings.SettingsWindow;


public class SettingsMenu extends Menu {
    private final SettingsWindow window;
    public SettingsMenu(Node mainWindow){
        super();
        window = new SettingsWindow(mainWindow);
        var label = new Button("Settings");
        label.setOnAction(e -> window.open());
        label.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        setGraphic(label);
    }
}
