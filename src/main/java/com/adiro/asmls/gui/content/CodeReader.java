package com.adiro.asmls.gui.content;

import java.io.File;
import java.io.IOException;

import com.adiro.asmls.logic.RegistersManager;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import com.adiro.asmls.gui.content.codeview.CodeView;
import com.adiro.asmls.logic.LogReader;


public class CodeReader extends HBox{
    private final CodeView codeView;
    private final RegistersManager registers;
    private final LogReader logReader;

    public CodeReader() {
        super();
        codeView = new CodeView();
        HBox.setHgrow(codeView, Priority.ALWAYS);
        VBox.setVgrow(codeView, Priority.ALWAYS);

        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(this, Priority.ALWAYS);

        RegistersView registersView = new RegistersView();
        registers = new RegistersManager(registersView);

        logReader = new LogReader(registersView, codeView);

        getChildren().addAll(codeView, registersView);
    }

    public void firstLine(){
        byte[] debugInfo;
        try{
            debugInfo = logReader.firstLine();
        }
        catch (IOException e){
            return;
        }
        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
    }

    public void lastLine(){
        byte[] debugInfo = logReader.readLastLine();
        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
        codeView.haltLine(codeView.getCurrentLine()+1);
    }

    public void nextLine() {
        byte[] debugInfo;
        try{
            debugInfo = logReader.readNextLine();
        }
        catch (IOException e){
            codeView.haltLine(codeView.getCurrentLine()+1);
            return;
        }
        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
    }

    public void prevLine() {
        byte[] debugInfo;
        try{
            debugInfo = logReader.readPrevLine();
        }
        catch (IOException e){
            firstLine();
            return;
        }
        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
    }

    public void nextBreakPoint(){
        byte[] debugInfo;
        do {
            try{
                debugInfo = logReader.readNextLine();
                codeView.goToLine(registers.readLineNumber(debugInfo));
            }
            catch (IOException e){
                firstLine(); // TODO: find more sophisticated way to do this
                lastLine();
                return;
            }
        } while(!codeView.breakPoints.contains(codeView.getCurrentLine()+1));

        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
    }

    public void previousBreakPoint(){
        byte[] debugInfo = null;
        do {
            try{
                debugInfo = logReader.readPrevLine();
                codeView.goToLine(registers.readLineNumber(debugInfo));
            }
            catch (IOException e){
                if (debugInfo == null) return;
                break;
            }
        } while(!codeView.breakPoints.contains(codeView.getCurrentLine()+1));

        codeView.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);
    }

    public void setSourceCodePath(String path) {
        codeView.setSourceCodePath(path);
        logReader.setLogDirectory(getFileDirectory(path) + "/debug");
    }

    public void refreshContent(){
        codeView.refresh();
    }

    private String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }
}

