package com.adiro.asmls.gui.content.codeview;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import com.adiro.asmls.gui.GuiColors;

import java.util.HashSet;
import java.util.Set;


public class CodeView extends StyledScrollPane {
    public final Set<Integer> breakPoints = new HashSet<>();
    private int currentLine = 0;
    private final LineCounter lineCounter;
    private final RawCodeView rawCodeView;

    public CodeView() {
        super();
        rawCodeView = new RawCodeView();
        lineCounter = new LineCounter(breakPoints);
        setContent(new HBox(lineCounter, rawCodeView));

        loadSourceCodeAndSetLineCounter();
    }

    private void loadSourceCodeAndSetLineCounter(){
        rawCodeView.loadSourceCode();
        lineCounter.setLineNumber(rawCodeView.sourceCode.size());
    }

    public void refresh(){
        loadSourceCodeAndSetLineCounter();
        currentLine = 0;
    }

    public void goToLine(int index) {
        if(index >= rawCodeView.sourceCode.size() - 1) return;

        currentLine = -1;
        int i = -1;
        do {
            goToNextLine();
            i++;
        }
        while(i < index);

        focusLine(currentLine);
    }

    private void focusLine(int lineNumber){
        setVvalue( (1.0 / (rawCodeView.sourceCode.size()-24.5))* (lineNumber - 0.2));
    }

    public void goToNextLine() {
        if(currentLine >= rawCodeView.sourceCode.size() - 1) return;

        do {
            currentLine++;
        }
        while(rawCodeView.getLineText(currentLine).isBlank());

        colorLine(currentLine, GuiColors.LINEPASSED);
    }

    private void colorLine(int lineNumber, Color color) {
        clearColors();
        rawCodeView.sourceCode.get(lineNumber).setBackground(
                new Background(
                        new BackgroundFill(
                                color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void clearColors() {
        for(var line : rawCodeView.sourceCode) {
            line.setBackground(
                    new Background(
                            new BackgroundFill(
                                    Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        }
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

    public void setSourceCodePath(String newPath){
        rawCodeView.setSourceCodePath(newPath);
    }
}

