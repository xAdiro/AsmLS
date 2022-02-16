package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.scene.control.Tooltip;

public class MoveManyNextButton extends MoveNextButton{

    public MoveManyNextButton(@NamedArg("contentView") ContentView contentView) {
        super(contentView);
        super.setText(">>");
        setTooltip(new Tooltip("Move 10 lines forward"));
    }

    @Override
    public void moveNext(ContentView contentView) {
        for(int i =0; i< 10; i++) {
            super.moveNext(contentView);
        }
    }
}

