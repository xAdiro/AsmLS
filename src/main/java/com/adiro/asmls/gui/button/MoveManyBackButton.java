package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.scene.control.Tooltip;

public class MoveManyBackButton extends MoveBackButton{

    public MoveManyBackButton(ContentView contentView) {
        super(contentView);
        setText("<<");
        setTooltip(new Tooltip("Move 10 lines backwards"));
    }


    @Override
    public void moveBack(ContentView contentView) {
        for(int i =0; i< 10; i++) {
            super.moveBack(contentView);
        }
    }
}

