package com.adiro.nasm_ide.gui.custom_blocks;

import com.adiro.nasm_ide.gui.GuiColors;

import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class DoubleText extends HBox{
	private Text value;
	public DoubleText(String label, String value, int fontSize) {
		super();
		var labelText = new Text(label);
		labelText.setFont(Font.font("Arial", FontPosture.REGULAR, fontSize));
		labelText.setFill(GuiColors.TEXT);
		var labelBox = new HBox(labelText);
		labelBox.setMinWidth(fontSize*2.5);
		getChildren().add(labelBox);
		
		
		this.value = new Text(value);
		this.value.setFont(Font.font("Arial", FontPosture.REGULAR, fontSize));
		this.value.setFill(GuiColors.TEXT);
		getChildren().add(this.value);
		
	}
	public void setValue(int newValue) {
		value.setText(Integer.toString(newValue));
	}
}
