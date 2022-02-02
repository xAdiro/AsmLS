package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MoveBackButton extends StyledButton{
	public MoveBackButton(ContentView contentView) {
		super("<");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				moveBack(contentView);
			}
		});
	
	}
	

	protected void moveBack(ContentView contentView) {
			contentView.prevLine();
	}

}
