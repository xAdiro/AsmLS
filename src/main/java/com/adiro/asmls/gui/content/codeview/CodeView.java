package com.adiro.asmls.gui.content.codeview;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.content.ContentView;

public class CodeView extends StyledScrollPane {
    private List<TextLine> sourceCode;
    private int currentLine = 0;
    private String sourceCodePath;
    private VBox code;
    private LineCounter lineCounter;

    public CodeView(ContentView contentView) {
        super();
        code = new VBox();
        code.setPadding(new Insets(0,0,0,5));
        code.setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND1, null, null)));
        HBox.setHgrow(code, Priority.ALWAYS);


        sourceCodePath = getPrevLocation();
        lineCounter = new LineCounter(0, contentView);
        loadSourceCode();


        var layout = new HBox(lineCounter, code);
        setContent(layout);
    }

    public void goToNextLine() {

        do {
            if(currentLine < sourceCode.size() - 1) {
                currentLine++;
            }
            else {
                return;
            }
        }
        while(sourceCode.get(currentLine).getText().isBlank());


        colorLine(currentLine, GuiColors.LINEPASSED);
    }

    public boolean goToLine(int index) {

        currentLine = -1;
        int i = -1;

        do {
            if(currentLine >= sourceCode.size()-1)
                return false;

            goToNextLine();
            i++;
        }
        while(i < index);

        setVvalue( (1.0 / (sourceCode.size()-24.5))* (currentLine - 0.2));
        return true;
    }

    public void setSourceCodePath(String newPath) {
        sourceCodePath = newPath;
        loadSourceCode();
    }

    public void haltLine(int index) {
        clearColors();
        try {
            colorLine(index, GuiColors.LINEERROR);
        }
        catch(Exception e) {
            System.out.println("[ERROR] No line to color");
        }

    }

    public int getCurrentLine() {
        return currentLine;
    }

    private boolean loadSourceCode() {
        File file = new File(sourceCodePath);
        sourceCode = new ArrayList<>();

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] Couldn't find the file " + file.getAbsolutePath());
            return false;
        }

        code.getChildren().clear();
        while(scanner.hasNextLine()) {
            var line = new Text(scanner.nextLine());

            Font font = Font.loadFont(App.class.getResource("fonts/CONSOLA.TTF").toExternalForm(), 23);
            line.setFont(font);

            line.setFill(GuiColors.TEXT);

            var textLine = new TextLine(line);
            sourceCode.add(textLine);
            code.getChildren().add(textLine);
        }
        scanner.close();

        lineCounter.setLineNumber(getLength());
        return true;
    }

    public void refresh(){
        loadSourceCode();
        currentLine = 0;
    }

    private void clearColors() {
        for(var line : sourceCode) {
            line.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    private void colorLine(int n, Color color) {
        clearColors();
        sourceCode.get(n).setBackground(
                new Background(
                        new BackgroundFill(
                                color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public String getPrevLocation() {

        String prevLocation = "";
        try{
            var resource = ".AsmLS-Config";
            BufferedReader br = new BufferedReader(new FileReader(resource));
            prevLocation = br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("[WARNING] Missing resources/locations/source in " + getClass());
        }

        if(prevLocation == null) prevLocation = "./";
        return prevLocation;
    }

    public int getLength() {
        return sourceCode.size();
    }

    private class TextLine extends TextFlow{

        private Text text;

        public TextLine(Text text) {

            super(text);
            this.text = text;
            setPadding(new Insets(0));
            setPadding(new Insets(2,0,2,0));
        }

        @SuppressWarnings("unused")
        public void setBackgroundColor(Color color) {
            setBackground(
                    new Background(
                            new BackgroundFill(
                                    color, CornerRadii.EMPTY, Insets.EMPTY)));
        }

        public String getText() {
            return text.getText();
        }
    }


}

