package com.adiro.nasm_ide.gui.menubar.textmenu;
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
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

class FileSubMenu extends MenuItem{
	public FileSubMenu(ContentView contentView, Stage stage){
		super();
		
		Label label = new Label("Open new file");
		label.setTextFill(Color.BLACK);
		label.setBackground(new Background(
    			new BackgroundFill(
    					Color.TRANSPARENT, null, null)));
		label.setPadding(new Insets(0));
		setGraphic(label);
		
		
		setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				changeFile(contentView, stage);
			}
		});
	}
	
private void changeFile(ContentView contentView, Stage stage) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		File defaultDir = new File(getPrevDirectory());
		fileChooser.setInitialDirectory(defaultDir);
		System.out.println(defaultDir);
		
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		String filePath;
		if(selectedFile != null) {
			
			filePath = selectedFile.getAbsolutePath();
			contentView.setSourceCodePath(filePath);
			setPrevLocation(filePath);
			DebugFileCreator.createRunFile(filePath);
			System.out.println("Selected: " + filePath);
		}
		else {
			System.out.println("No file selected, nothing to do");
		}
	}
	
	private String getPrevDirectory() {
		
		Path path = Paths.get("resources/locations/source");
		String prevLocation = "";
		
		try {
			prevLocation = Files.readAllLines(path).get(0);
			prevLocation = getFileDirectory(prevLocation);
			
			
		} catch (IOException e) {
			System.out.println(
					"[WARNING] Missing resources/locations/source, the program might be corrupted");
		}
		
		if(prevLocation.isBlank()) prevLocation = "./";
		return prevLocation;
	}
	
	private boolean setPrevLocation(String location) {
		try {
			FileWriter writer = new FileWriter("resources/locations/source");
			writer.write(location);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.print(
					"[WARNING] Missing resources/locations/source, the program might be corrupted");
		}
		return false;
		
	}



	private String getFileDirectory(String filePath) {
		File sourceFile = new File(filePath);
		return sourceFile.getParent();
	}
}
