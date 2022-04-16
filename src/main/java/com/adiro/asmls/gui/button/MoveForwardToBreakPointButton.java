package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.CodeReader;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveForwardToBreakPointButton extends StyledButton {
    public MoveForwardToBreakPointButton(CodeReader contentView) {
        super();
        setDefaultIcon();
        setTooltip(new Tooltip("Move to the next breakpoint"));

        setOnAction(e -> {
            //setLoadingIcon(); TODO: need to make this work (javafx thread is stopped because of following line)
            contentView.nextBreakPoint();
            setDefaultIcon();
        });
    }

    private void setDefaultIcon(){
        setGraphic(ResourceSupplier.Icons.nextBreakpoint);
    }

    private void setLoadingIcon(){setGraphic(ResourceSupplier.Icons.loading);}
}

