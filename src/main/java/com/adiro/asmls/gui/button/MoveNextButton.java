package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveNextButton extends StyledButton{
    public MoveNextButton(ContentView contentView) {
        super(">");
        setTooltip(new Tooltip("Move to next line"));
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

