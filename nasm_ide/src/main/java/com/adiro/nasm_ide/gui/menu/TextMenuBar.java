package com.adiro.nasm_ide.gui.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class TextMenuBar extends MenuBar{
	
	public TextMenuBar() {
		super();
		
		Menu menuFile = new Menu("Plik");
		Menu menuEdit = new Menu("Edytuj");
		getMenus().addAll(menuFile, menuEdit);
	}

}
