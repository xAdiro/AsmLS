package com.adiro.asmls.gui.content.codeview;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import com.adiro.asmls.gui.GuiColors;

public class StyledScrollPane extends ScrollPane{
    public StyledScrollPane() {
        super();
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setFitToWidth(true);
        setFitToHeight(true);
        setPadding(new Insets(0));

        setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND2, null, null)));
        applyCss();
    }

}

