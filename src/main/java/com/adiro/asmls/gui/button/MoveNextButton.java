package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveNextButton extends StyledButton{
    public MoveNextButton(CodeReader contentView) {
        super();
        setGraphic(ResourceSupplier.Icons.next);
        getStyleClass().add("next-button");
        setTooltip(new Tooltip("Move to next line"));
        this.setOnAction(e -> moveNext(contentView));

    }

    protected void moveNext(CodeReader contentView) {
        contentView.nextLine();
    }

}

