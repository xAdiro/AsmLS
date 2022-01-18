package com.adiro.nasm_ide;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SourceButton extends Button{
	public SourceButton(SourceViewArea sourceCode, Stage stage) {
		super("Zmień lokalizacje pliku źródłowego");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				changeFile(sourceCode, stage);
			}
		});
	
	}
	

	private void changeFile(SourceViewArea sourceCode, Stage stage) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		
		File defaultDir = new File("./");
		fileChooser.setInitialDirectory(defaultDir);
		
		File selectedFile = fileChooser.showOpenDialog(stage);
		
		var filePath = selectedFile.getAbsolutePath();
		System.out.println(filePath);
		
		sourceCode.setSourceCodePath(filePath);
		
		
	}

}
