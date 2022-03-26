package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;

import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.beans.NamedArg;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveManyNextButton extends MoveNextButton{

    public MoveManyNextButton(@NamedArg("contentView") ContentView contentView) {
        super(contentView);
        setGraphic(ResourceSupplier.Icons.manyNext);
        setTooltip(new Tooltip("Move 10 lines forward"));
    }

    @Override
    public void moveNext(ContentView contentView) {
        for(int i =0; i< 10; i++) {
            super.moveNext(contentView);
        }
    }
}

