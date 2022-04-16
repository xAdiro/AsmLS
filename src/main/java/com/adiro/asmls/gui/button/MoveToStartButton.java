package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveToStartButton extends StyledButton{
    public MoveToStartButton(CodeReader contentView) {
        super();
        setGraphic(ResourceSupplier.Icons.toBegin);
        setTooltip(new Tooltip("Move to start of program"));

        this.setOnAction(e -> moveToEnd(contentView));
    }

    private void moveToEnd(CodeReader contentView) {
        contentView.firstLine();
    }
}

