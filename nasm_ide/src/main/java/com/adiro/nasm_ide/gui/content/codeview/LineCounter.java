package com.adiro.nasm_ide.gui.content.codeview;

import com.adiro.nasm_ide.gui.GuiColors;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

class LineCounter extends VBox{
	private int numberOfLines;
	
	public LineCounter(int numberOfLines) {
		super();
		setBackground(new Background(
    			new BackgroundFill(
    					GuiColors.BACKGROUND1, null, null)));
		
		for(int i = 1; i<=numberOfLines;i++) {
			
			getChildren().add(new LineText(i));
		}
		this.numberOfLines = numberOfLines;
		
		
    	setBorder(new Border(
    			new BorderStroke(
    					Color.DARKGREY, 
    					BorderStrokeStyle.SOLID, 
    					null, 
    					new BorderWidths(0,5,0,0))));
	}
	
	public void setLineNumber(int newValue) {
		if(newValue > numberOfLines) {
			while(newValue > numberOfLines) {
				numberOfLines++;
				getChildren().add(new LineText(numberOfLines));
			}
			return;
		}
		
		if(newValue < numberOfLines) {
			while(newValue < numberOfLines) {
				numberOfLines--;
				getChildren().remove(numberOfLines);
			}
			return;
		}
	}
	
	private class LineText extends Text{
		public LineText(int number) {
			super(Integer.toString(number) + " "); // lazy margin
			setFont(Font.font("Noto Sans Mono", FontPosture.REGULAR, 20));
			setFill(GuiColors.TEXT2);
		}
	}
}
