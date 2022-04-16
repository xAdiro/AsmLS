package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveManyNextButton extends MoveNextButton{

    public MoveManyNextButton(CodeReader contentView) {
        super(contentView);
        setGraphic(ResourceSupplier.Icons.manyNext);
        setTooltip(new Tooltip("Move 10 lines forward"));
    }

    @Override
    public void moveNext(CodeReader contentView) {
        for(int i =0; i< 10; i++) {
            super.moveNext(contentView);
        }
    }
}

