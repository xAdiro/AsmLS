package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveToEndButton extends StyledButton{
    public MoveToEndButton(CodeReader contentView) {
        super();
        setGraphic(ResourceSupplier.Icons.toEnd);
        setTooltip(new Tooltip("Move to end of program"));

        this.setOnAction(e -> moveToEnd(contentView));
    }

    private void moveToEnd(CodeReader contentView) {
        contentView.lastLine();
    }
}
