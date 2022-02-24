package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;
import javafx.concurrent.Task;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

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
        setGraphic(new ImageView(App.class.getResource("icons/previous-breakpoint.png").toExternalForm()));
    }

    private void setLoadingIcon(){
        setGraphic(new ImageView(App.class.getResource("icons/loading.png").toExternalForm()));
    }
}
