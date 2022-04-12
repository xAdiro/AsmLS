package com.adiro.asmls.gui.window.settings;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SettingsWindow extends Stage {
    Node mainWindow;
    public SettingsWindow(Node mainWindow){
        super();
        this.mainWindow = mainWindow;
        var scene = new Scene(new SettingsContent(), 800, 600);

        setScene(scene);
        setTitle("Settings");
        scene.getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::close);
    }

    public void open(){
        mainWindow.setDisable(true);
        show();
    }

    public void close(WindowEvent event){
        hide();
        mainWindow.setDisable(false);
    }
}
