package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveToEndButton extends StyledButton{
    public MoveToEndButton(ContentView contentView) {
        super();
        setGraphic(ResourceSupplier.Icons.toEnd);
        setTooltip(new Tooltip("Move to end of program"));

        this.setOnAction(e -> moveToEnd(contentView));
    }

    private void moveToEnd(ContentView contentView) {
        contentView.lastLine();
    }
}
