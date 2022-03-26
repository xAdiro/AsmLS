package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveForwardToBreakPointButton extends StyledButton {
    public MoveForwardToBreakPointButton(ContentView contentView) {
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

    private void setLoadingIcon(){
        setGraphic(new ImageView(App.class.getResource("icons/loading.png").toExternalForm()));
    }
}

