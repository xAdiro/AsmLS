package com.adiro.asmls.gui.menubar.textmenu;

import java.io.*;
import java.nio.file.Path;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.logic.DebugFileCreator;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class FileSubMenu extends MenuItem{

    public FileSubMenu(@NamedArg("contentView") ContentView contentView, @NamedArg("stage") Stage stage){
        super();

        Label label = new Label("Open new file");
        label.setTextFill(Color.BLACK);
        label.setBackground(new Background(
                new BackgroundFill(
                        Color.TRANSPARENT, null, null)));
        label.setPadding(new Insets(0));
        setGraphic(label);


        setOnAction(e -> changeFile(contentView, stage));
    }


    private void changeFile(ContentView contentView, Stage stage) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File defaultDir = new File(getPrevDirectory());
        fileChooser.setInitialDirectory(defaultDir);
        System.out.println(defaultDir);


        File selectedFile = fileChooser.showOpenDialog(stage);

        String filePath;
        if(selectedFile != null) {

            filePath = selectedFile.getAbsolutePath();
            contentView.setSourceCodePath(filePath);
            setPrevLocation(filePath);
            DebugFileCreator.createRunFile(filePath);
            System.out.println("Selected: " + filePath);
        }
        else {
            System.out.println("No file selected, nothing to do");
        }
    }

    private String getPrevDirectory() {
        String prevLocation = "";

        try {
            var resource = ".source";
            BufferedReader br = new BufferedReader(new FileReader(resource));
            prevLocation = br.readLine();
            br.close();
            prevLocation = getFileDirectory(prevLocation);
        } catch (IOException e) {
            System.out.println(
                    "[WARNING] Missing resources/locations/source, the program might be corrupted");
        }
        if(prevLocation == null) prevLocation = "./";
        return prevLocation;
    }

    private void setPrevLocation(String location) {
        try {
           PrintWriter writer = new PrintWriter(new File(".source"));
           writer.print(location);
           writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            System.out.println("[WARNING] Missing resources/locations/source, the program might be corrupted");
        }
    }



    private String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }
}

