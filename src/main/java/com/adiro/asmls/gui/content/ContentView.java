package com.adiro.asmls.gui.content;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.adiro.asmls.gui.content.codeview.CodeView;
import com.adiro.asmls.logic.LogReader;
import javafx.beans.NamedArg;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ContentView extends HBox{

    private final RegistersView registersView;
    private final CodeView codeView;
    private final LogReader logReader;
    private Set<Integer> breakPoints;

    public ContentView() {

        super();
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(this, Priority.ALWAYS);

       registersView = new RegistersView();
        codeView = new CodeView(this);
        HBox.setHgrow(codeView, Priority.ALWAYS);
        VBox.setVgrow(codeView, Priority.ALWAYS);
        logReader = new LogReader(registersView, codeView);

        getChildren().addAll(codeView, registersView);

        breakPoints = new HashSet<Integer>();
    }
    public void firstLine(){
        logReader.goToFirstLine();
    }

    public void lastLine(){
        logReader.goToLastLine();
    }

    public void nextLine() {
        logReader.nextLine();
    }

    public boolean prevLine() {
        return logReader.prevLine();
    }

    public void nextBreakPoint(){
        logReader.goForwardToBreakPoint(breakPoints);
    }

    public void previousBreakPoint(){
        logReader.goBackwardsToBreakPoint(breakPoints);
    }

    public void addBreakPoint(int lineNumber){
        breakPoints.add(lineNumber);
    }

    public void removeBreakPoint(int lineNumber){
        breakPoints.remove(lineNumber);
    }

    public void setSourceCodePath(String path) {
        codeView.setSourceCodePath(path);
        logReader.setLogDirectory(getFileDirectory(path) + "/debug/");
    }

    public void refreshContent(){
        codeView.refresh();
    }

    private String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }
}

