package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;

import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.beans.NamedArg;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveManyBackButton extends MoveBackButton{

    public MoveManyBackButton(ContentView contentView) {
        super(contentView);
        setGraphic(ResourceSupplier.Icons.manyPrevious);
        setTooltip(new Tooltip("Move 10 lines backwards"));
    }


    @Override
    public void moveBack(ContentView contentView) {
        for(int i =0; i< 10; i++) {
            super.moveBack(contentView);
        }
    }
}

