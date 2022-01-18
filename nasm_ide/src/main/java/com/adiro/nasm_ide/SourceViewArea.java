package com.adiro.nasm_ide;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SourceViewArea extends ScrollPane {
	
	List<Text> code = new ArrayList<>();
	private int currentLine = -1;
	
	public SourceViewArea() {
		super();
    	this.setMaxSize(1000, 700);
    	this.setMinSize(1000, 700);
    	this.setFocusTraversable(false);
    	setSourceCode();
	}
	
	public void setCurrentLine(int n) {
		currentLine = n;
	}
	
	public void NextLine() {
		
		do {
			if(currentLine < code.size()-1) {
				currentLine++;
			}
			
			System.out.print(code.get(currentLine).getText());
		}
		while(code.get(currentLine).getText().trim() == "");
		
		colorLine(currentLine);
	}
	
	public boolean setLine(String label) {
		
		currentLine = -1;
		
		do {
			if(currentLine >= code.size()-1)
				return false;
			
			NextLine();
		}
		while(!code.get(currentLine).getText().trim().equals(label+":"));
		//NextLine();
		
		
		
		return true;
	}
	
	public void haltCode() {
		clearColors();
		code.get(currentLine+1).setFill(Color.RED);
		
	}
	public int getCurrentLine() {
		return currentLine;
	}
	
	private void setSourceCode() {
		File file = new File("/home/adrian/Dokumenty/Dos/ide/zegar.ASM");
    	
    	Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.print("nie znaleziono pliku");
		}
    	
    	while(scanner.hasNextLine()) {
    		code.add(new Text(scanner.nextLine() + "\n"));
    	}
    	scanner.close();
    	
    	var text = new TextFlow();
    	
    	for(var line : code ) {
    		line.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
    		text.getChildren().add(line);
    	}
    	
    	this.setContent(text);
    	
    	
	}
	
	private void clearColors() {
		for(var line : code) {
			line.setFill(Color.BLACK);
		}
	}
	
	private void colorLine(int n) {
		
		clearColors();
		code.get(n).setFill(Color.GREEN);
	}
}
