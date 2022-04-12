package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveManyNextButton extends MoveNextButton{

    public MoveManyNextButton(ContentView contentView) {
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

