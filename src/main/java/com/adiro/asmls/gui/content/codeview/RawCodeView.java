package com.adiro.asmls.gui.content.codeview;

import com.adiro.asmls.gui.GuiColors;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RawCodeView extends VBox {
    private String sourceCodePath;
    public List<TextLine> sourceCode;

    public RawCodeView(){
        super();
        setStyle();
        sourceCodePath = getPrevLocation();
        loadSourceCode();
    }

    private void setStyle(){
        setPadding(new Insets(0,0,0,5));
        setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND1, null, null)));
        HBox.setHgrow(this, Priority.ALWAYS);
    }

    private String getPrevLocation() {
        String prevLocation = readPrevLocationFile();
        if(prevLocation == null) prevLocation = "./";
        return prevLocation;
    }

    private String readPrevLocationFile(){
        String prevLocation = null;
        try{
            var resource = ".AsmLS-Config";
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            prevLocation = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("[WARNING] Missing resources/locations/source in " + getClass());
        }
        return prevLocation;
    }

    void loadSourceCode(){
        File file = new File(sourceCodePath);
        sourceCode = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Couldn't find the file " + file.getAbsolutePath());
            return;
        }

        getChildren().clear();
        while(scanner.hasNextLine()) {
            var textLine = new TextLine(scanner.nextLine());
            sourceCode.add(textLine);
            getChildren().add(textLine);
        }
        scanner.close();
    }

    void setSourceCodePath(String newPath) {
        sourceCodePath = newPath;
        loadSourceCode();
    }

    String getLineText(int lineNumber){
        return sourceCode.get(lineNumber).getText();
    }
}
