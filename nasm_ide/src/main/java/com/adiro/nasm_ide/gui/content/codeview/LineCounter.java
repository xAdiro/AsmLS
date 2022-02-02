package com.adiro.nasm_ide.gui.content.codeview;

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
		
		for(int i = 1; i<=numberOfLines;i++) {
			
			getChildren().add(new LineText(i));
			System.out.println("dodaje: " + i);
		}
		this.numberOfLines = numberOfLines;
		
		
    	setBorder(new Border(
    			new BorderStroke(
    					Color.GRAY, 
    					BorderStrokeStyle.SOLID, 
    					null, 
    					new BorderWidths(0,1,0,0))));
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
			super(Integer.toString(number));
			setFont(Font.font("Arial", FontPosture.REGULAR, 20));
			setFill(Color.GRAY);
		}
	}
}
