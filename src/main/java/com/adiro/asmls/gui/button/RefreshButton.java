package com.adiro.asmls.gui.button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.logic.DebugFileCreator;
import com.adiro.asmls.utilities.ResourceSupplier;


public class RefreshButton extends StyledButton{
    ContentView contentView;
    public RefreshButton(ContentView contentView) {
        super();
        setGraphic(ResourceSupplier.Icons.refresh);
        setTooltip(new Tooltip("Refresh for changes"));
        this.contentView = contentView;

        this.setOnAction(e -> {
            contentView.refreshContent();
            generateNewFile();
        });
    }

    private void generateNewFile() {
        new DebugFileCreator(getPrevFile()).createRunFile();
    }

    private String getPrevFile() {
        String prevLocation = "";
        try {
            var resource = ".AsmLS-Config";
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            prevLocation = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.print(getClass().toString());
            System.out.println("[WARNING] Missing resources/locations/source, the program might be corrupted");
        }

        if(prevLocation == null) prevLocation = "./";
        return prevLocation;
    }
}

