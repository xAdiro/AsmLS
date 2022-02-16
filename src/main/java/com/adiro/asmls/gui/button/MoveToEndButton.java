package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveToEndButton extends StyledButton{
    public MoveToEndButton(@NamedArg("contentView") ContentView contentView) {
        super(">|");
        setTooltip(new Tooltip("Move to end of program"));
        this.setOnAction(e -> moveToEnd(contentView));

    }


    private void moveToEnd(ContentView contentView) {

        while(contentView.nextLine()) {

        }
    }

}
