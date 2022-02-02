package com.adiro.nasm_ide.gui.menubar;

import com.adiro.nasm_ide.gui.button.GenerateDebugFileButton;
import com.adiro.nasm_ide.gui.button.MoveBackButton;
import com.adiro.nasm_ide.gui.button.MoveManyBackButton;
import com.adiro.nasm_ide.gui.button.MoveManyNextButton;
import com.adiro.nasm_ide.gui.button.MoveNextButton;
import com.adiro.nasm_ide.gui.button.MoveToEndButton;
import com.adiro.nasm_ide.gui.button.MoveToStartButton;
import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IconMenuBar extends VBox{
	
	public IconMenuBar(ContentView contentView, Stage stage) {
		super();
		
		var b1 = new GenerateDebugFileButton();
		var b2 = new MoveToStartButton(contentView);
		var b3 = new MoveBackButton(contentView);
		var b4 = new MoveNextButton(contentView);
		var b5 = new MoveManyNextButton(contentView);
		var b6 = new MoveToEndButton(contentView);
		
		var buttons = new ButtonSet(
				new GenerateDebugFileButton(),
				new MoveToStartButton(contentView),
				new MoveManyBackButton(contentView),
				new MoveBackButton(contentView),
				new MoveNextButton(contentView),
				new MoveManyNextButton(contentView),
				new MoveToEndButton(contentView)
				);
		
		setPadding(new Insets(5));
		setSpacing(5);
		
		
		getChildren().addAll(buttons);
	}
	
	private class ButtonSet extends HBox{
		public ButtonSet(Button... buttons){
			super(buttons);
			
		}
	}

}
