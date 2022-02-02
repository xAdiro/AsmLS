package com.adiro.nasm_ide.gui.menubar.textmenu;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class TextMenuBar extends MenuBar{
	
	public TextMenuBar(ContentView contentView, Stage stage) {
		super();
		
		Menu menuFile = new Menu("File");
		menuFile.getItems().add(new FileSubMenu(contentView, stage));
		Menu menuEdit = new Menu("Edit");
		Menu menuSettings = new Menu("Settings");
		getMenus().addAll(menuFile, menuEdit, menuSettings);
	}

}
