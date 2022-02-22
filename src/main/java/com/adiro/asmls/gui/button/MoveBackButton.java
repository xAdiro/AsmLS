package com.adiro.asmls.gui.button;

import com.adiro.asmls.App;
import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class MoveBackButton extends StyledButton{
    public MoveBackButton(@NamedArg("contentView") ContentView contentView) {
        setGraphic(new ImageView(App.class.getResource("icons/previous.png").toExternalForm()));
        setTooltip(new Tooltip("Move to previous line"));
        this.setOnAction(e -> moveBack(contentView));

    }


    protected void moveBack(ContentView contentView) {
        contentView.prevLine();
    }

}

