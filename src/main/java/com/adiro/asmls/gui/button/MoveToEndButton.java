package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveToEndButton extends StyledButton{
    public MoveToEndButton(ContentView contentView) {
        super(">|");
        setTooltip(new Tooltip("Move to end of program"));
        this.setOnAction(new EventHandler<ActionEvent> () {
            @Override public void handle(ActionEvent e) {
                moveToEnd(contentView);
            }
        });

    }


    private void moveToEnd(ContentView contentView) {

        while(contentView.nextLine()) {

        }
    }

}
