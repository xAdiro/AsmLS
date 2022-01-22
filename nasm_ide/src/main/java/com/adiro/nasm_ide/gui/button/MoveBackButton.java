package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MoveBackButton extends Button{
	public MoveBackButton(ContentView contentView) {
		super("<");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				moveBack(contentView);
			}
		});
	
	}
	

	private void moveBack(ContentView contentView) {
			contentView.prevLine();
	}

}
