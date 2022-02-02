package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

public class MoveManyBackButton extends MoveBackButton{
	
	public MoveManyBackButton(ContentView contentView) {
		super(contentView);
		setText("<<");
	}

	
	@Override
	public void moveBack(ContentView contentView) {
		for(int i =0; i< 10; i++) {
			super.moveBack(contentView);
		}
	}
}
