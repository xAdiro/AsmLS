package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveBackButton extends StyledButton{
    public MoveBackButton(ContentView contentView) {
        super("<");
        setTooltip(new Tooltip("Move to previous line"));
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

