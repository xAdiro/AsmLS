package com.adiro.nasm_ide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class SourceViewArea extends ScrollPane {
	
	List<Text> code;
	private int currentLine = -1;
	private String sourceCodePath = "source.ASM";
	
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
	
	public void setSourceCodePath(String newPath) {
		sourceCodePath = newPath;
		setSourceCode();
	}
	
	public void haltCode() {
		clearColors();
		try {
			code.get(currentLine+1).setFill(Color.RED);
		}
		catch(Exception e) {
			System.out.println("brak linii do zaznaczenia");
		}
		
	}
	public int getCurrentLine() {
		return currentLine;
	}
	
	private boolean setSourceCode() {
		File file = new File(sourceCodePath);
		
		code = new ArrayList<>();
    	
    	Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.print("nie znaleziono pliku");
			
			return false;
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
    	
    	return true;
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
