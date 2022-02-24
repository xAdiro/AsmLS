package com.adiro.asmls.gui.button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.logic.DebugFileCreator;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class RefreshButton extends StyledButton{
    ContentView contentView;
    public RefreshButton(@NamedArg("contentView") ContentView contentView) {
        super();
        setGraphic(new ImageView(App.class.getResource("icons/refresh.png").toExternalForm()));
        setTooltip(new Tooltip("Refresh for changes"));
        this.contentView = contentView;

        this.setOnAction(e -> {
            contentView.refreshContent();
            generateNewFile();
        });

    }

    private void generateNewFile() {
        DebugFileCreator.createRunFile(getPrevFile());
    }

    private String getPrevFile() {

        String prevLocation = "";

        try {
            var resource = ".AsmLS-Config";
            BufferedReader br = new BufferedReader(new FileReader(resource));
            prevLocation = br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.print(getClass().toString());
            System.out.println("[WARNING] Missing resources/locations/source, the program might be corrupted");
        }

        if(prevLocation == null) prevLocation = "./";
        return prevLocation;
    }
}

