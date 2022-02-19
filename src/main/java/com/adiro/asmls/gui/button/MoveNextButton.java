package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;

public class MoveNextButton extends StyledButton{
    public MoveNextButton(@NamedArg("contentView") ContentView contentView) {
        super(">");
        System.out.println(contentView == null);
        setTooltip(new Tooltip("Move to next line"));
        this.setOnAction(e -> moveNext(contentView));

    }


    protected void moveNext(ContentView contentView) {
        contentView.nextLine();
    }

}

