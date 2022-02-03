package com.adiro.nasm_ide.gui.menubar;

import com.adiro.nasm_ide.gui.GuiColors;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IconMenuBar extends VBox{
	
	public IconMenuBar(ContentView contentView, Stage stage) {
		super();
		
		var buttons = new ButtonSet(
				new MoveToStartButton(contentView),
				new MoveManyBackButton(contentView),
				new MoveBackButton(contentView),
				new MoveNextButton(contentView),
				new MoveManyNextButton(contentView),
				new MoveToEndButton(contentView),
				new GenerateDebugFileButton()
				);
		
		getChildren().addAll(buttons);
		
		
		setPadding(new Insets(5));
		setBackground(
				new Background(
						new BackgroundFill(
								GuiColors.BACKGROUND2,null,null)));
	}
	
	private class ButtonSet extends HBox{
		public ButtonSet(Button... buttons){
			super(buttons);
			setSpacing(5);
		}
	}

}
