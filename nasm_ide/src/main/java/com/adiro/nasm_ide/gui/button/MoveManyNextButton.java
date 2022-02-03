package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.scene.control.Tooltip;

public class MoveManyNextButton extends MoveNextButton{

		public MoveManyNextButton(ContentView contentView) {
			super(contentView);
			super.setText(">>");
			setTooltip(new Tooltip("Move 10 lines forward"));
		}
		
		@Override
		public void moveNext(ContentView contentView) {
			for(int i =0; i< 10; i++) {
				super.moveNext(contentView);
			}
		}
}
