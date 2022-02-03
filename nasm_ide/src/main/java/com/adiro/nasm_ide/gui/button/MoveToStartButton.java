package com.adiro.nasm_ide.gui.button;

import com.adiro.nasm_ide.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveToStartButton extends StyledButton{
	public MoveToStartButton(ContentView contentView) {
		super("|<");
		setTooltip(new Tooltip("Move to start of program"));
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				moveToEnd(contentView);
			}
		});
	
	}
	

	private void moveToEnd(ContentView contentView) {
			
		while(contentView.prevLine()) {}
			
	}

}
