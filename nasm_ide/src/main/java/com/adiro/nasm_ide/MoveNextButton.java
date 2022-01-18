package com.adiro.nasm_ide;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class MoveNextButton extends Button{
	public MoveNextButton(SourceViewArea sourceArea, LogReader reader) {
		super(">");
		this.setOnAction(new EventHandler<ActionEvent> () {
			@Override public void handle(ActionEvent e) {
				moveNext(sourceArea, reader);
			}
		});
	
	}
	

	private void moveNext(SourceViewArea sourceArea, LogReader reader) {
		
			
			reader.NextLine();
		
		
	}

}
