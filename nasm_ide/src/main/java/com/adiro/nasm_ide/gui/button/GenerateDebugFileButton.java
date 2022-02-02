package com.adiro.nasm_ide.gui.button;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.adiro.nasm_ide.gui.content.ContentView;
import com.adiro.nasm_ide.logic.DebugFileCreator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GenerateDebugFileButton extends StyledButton{
	public GenerateDebugFileButton() {
		super(new ImageView("file:resources/icons/refresh.png"));
		
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				generateFile();
			}
		});
	
	}
	
	private void generateFile() {
	
		DebugFileCreator.createRunFile(getPrevFile());

	}
	
	private String getPrevFile() {
		
		Path path = Paths.get("resources/locations/source");
		String prevLocation = "";
		
		try {
			prevLocation = Files.readAllLines(path).get(0);
		} catch (IOException e) {
			System.out.println("[WARNING] Missing resources/locations/source, the program might be corrupted");
		}
		
		if(prevLocation.isBlank()) prevLocation = "./";
		return prevLocation;
	}
}
