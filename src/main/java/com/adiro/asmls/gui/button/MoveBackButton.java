package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;
import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveBackButton extends StyledButton{
    public MoveBackButton(ContentView contentView) {
        setGraphic(ResourceSupplier.Icons.previous);
        setTooltip(new Tooltip("Move to previous line"));
        this.setOnAction(e -> moveBack(contentView));

    }


    protected void moveBack(ContentView contentView) {
        contentView.prevLine();
    }

}

