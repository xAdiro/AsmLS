package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MoveNextButton extends StyledButton{
	public MoveNextButton(ContentView contentView) {
		super(">");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				moveNext(contentView);
			}
		});
	
	}
	

	protected void moveNext(ContentView contentView) {
			contentView.nextLine();
	}

}
