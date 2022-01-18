package com.adiro.nasm_ide;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class changeFileButton extends Button{
	public changeFileButton(LogReader reader, Stage stage) {
		super("Zmień lokalizacje plików");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				changeDir(reader, stage);
			}
		});
	
	}
	

	private void changeDir(LogReader reader, Stage stage) {
		
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Open Resource File");
		
		File defaultDir = new File("./");
		dirChooser.setInitialDirectory(defaultDir);
		
		File selectedDir = dirChooser.showDialog(stage);
		
		reader.setFile(selectedDir.getAbsolutePath()+ "/");
		
		
	}

}
