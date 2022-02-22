package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveManyBackButton extends MoveBackButton{

    public MoveManyBackButton(@NamedArg("contentView") ContentView contentView) {
        super(contentView);
        setGraphic(new ImageView(App.class.getResource("icons/previous-many.png").toExternalForm()));
        setTooltip(new Tooltip("Move 10 lines backwards"));
    }


    @Override
    public void moveBack(ContentView contentView) {
        for(int i =0; i< 10; i++) {
            super.moveBack(contentView);
        }
    }
}

