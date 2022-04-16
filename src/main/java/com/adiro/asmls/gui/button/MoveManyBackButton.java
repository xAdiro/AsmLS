package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveManyBackButton extends MoveBackButton{
    public MoveManyBackButton(CodeReader contentView) {
        super(contentView);
        setGraphic(ResourceSupplier.Icons.manyPrevious);
        setTooltip(new Tooltip("Move 10 lines backwards"));
    }

    @Override
    public void moveBack(CodeReader contentView) {
        for(int i =0; i< 10; i++) {
            super.moveBack(contentView);
        }
    }
}

