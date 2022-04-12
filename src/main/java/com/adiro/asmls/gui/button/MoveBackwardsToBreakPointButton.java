package com.adiro.asmls.gui.button;

import javafx.scene.control.Tooltip;

import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;


public class MoveBackwardsToBreakPointButton extends StyledButton{
    public MoveBackwardsToBreakPointButton(ContentView contentView) {
        super();
        setDefaultIcon();
        setTooltip(new Tooltip("Move to the previous breakpoint"));

        setOnAction(e -> {
            //setLoadingIcon(); TODO: need to make this work (javafx thread is stopped because of following line)
            contentView.previousBreakPoint();
            setDefaultIcon();
        });
    }
    private void setDefaultIcon(){
        setGraphic(ResourceSupplier.Icons.previousBreakpoint);
    }

    private void setLoadingIcon(){
        setGraphic(ResourceSupplier.Icons.loading);
    }
}
