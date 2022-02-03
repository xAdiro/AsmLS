package com.adiro.nasm_ide.gui.content.codeview;

import com.adiro.nasm_ide.gui.GuiColors;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class StyledScrollPane extends ScrollPane{
	
	public StyledScrollPane() {
		super();
		setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    	setFitToWidth(true);
    	setPadding(new Insets(0));
    	
    	setBackground(new Background(
    			new BackgroundFill(
    					GuiColors.BACKGROUND2, null, null)));
    	applyCss();
	}

}
