package com.adiro.nasm_ide.gui.content;

import java.io.File;

import com.adiro.nasm_ide.gui.content.codeview.CodeView;
import com.adiro.nasm_ide.logic.LogReader;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class ContentView extends HBox{
	
		private RegistersView registersView;
		private CodeView scView;
		private LogReader logReader;
	
		public ContentView() {
			
			super();
			HBox.setHgrow(this, Priority.ALWAYS);
			setMaxWidth(Double.MAX_VALUE);
			
			this.registersView = new RegistersView();
			this.scView = new CodeView();
			this.logReader = new LogReader(registersView, scView);
			
			getChildren().addAll(scView, registersView);
			
		}
		
		public boolean nextLine() {
			return logReader.nextLine();
		}
		
		public boolean prevLine() {
			return logReader.prevLine();
		}
		
		//public void haltLine() {
		//	scView.haltLine();
		//}
		
		public void setSourceCodePath(String path) {
			scView.setSourceCodePath(path);
			
			logReader.setLogDirectory(getFileDirectory(path) + "/debug/");
		}
		
		private String getFileDirectory(String filePath) {
			File sourceFile = new File(filePath);
			return sourceFile.getParent();
		}
}
