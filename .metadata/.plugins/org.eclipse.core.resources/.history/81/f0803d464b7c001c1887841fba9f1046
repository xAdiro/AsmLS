package com.adiro.nasm_ide.gui.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public abstract class StyledButton extends Button {
	
	StyledButton(){
		super();
		setStyle();
		
	}
	
	StyledButton(String label){
		super(label);
		setStyle();
		
	}
	
	StyledButton(ImageView icon){
		super();
		icon.setPreserveRatio(true);
		icon.setFitHeight(17);
		setGraphic(icon);
		setStyle();
	}
	
	private void setStyle() {
		setPadding(new Insets(0));
		setSize(30 ,30);
	}
	
	private void setSize(int width, int height) {
		
		setMinWidth(width);
		setMaxWidth(width);
		setMinHeight(height);
		setMaxHeight(height);
	}
	

}
