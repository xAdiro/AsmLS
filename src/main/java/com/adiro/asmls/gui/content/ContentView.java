package com.adiro.asmls.gui.content;

import java.io.File;
import com.adiro.asmls.gui.content.codeview.CodeView;
import com.adiro.asmls.logic.LogReader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ContentView extends HBox{

    private final RegistersView registersView;
    private final CodeView codeView;
    private final LogReader logReader;

    public ContentView() {

        super();
        HBox.setHgrow(this, Priority.ALWAYS);
        setMaxWidth(Double.MAX_VALUE);

       registersView = new RegistersView();
        codeView = new CodeView();
        logReader = new LogReader(registersView, codeView);

        getChildren().addAll(codeView, registersView);
    }

    public boolean nextLine() {
        return logReader.nextLine();
    }

    public boolean prevLine() {
        return logReader.prevLine();
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

