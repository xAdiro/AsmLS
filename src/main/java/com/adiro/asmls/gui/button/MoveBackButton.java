package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveBackButton extends StyledButton{
    public MoveBackButton(ContentView contentView) {
        setGraphic(ResourceSupplier.Icons.previous);
        setTooltip(new Tooltip("Move to previous line"));

        setOnAction(e -> moveBack(contentView));
    }

    protected void moveBack(ContentView contentView) {
        contentView.prevLine();
    }
}

