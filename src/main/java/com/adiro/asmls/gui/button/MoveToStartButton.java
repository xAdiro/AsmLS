package com.adiro.asmls.gui.button;

import com.adiro.asmls.gui.content.ContentView;

import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import org.jetbrains.annotations.NotNull;

public class MoveToStartButton extends StyledButton{

    public MoveToStartButton(@NamedArg("contentView") ContentView contentView) {
        super("|<");
        setTooltip(new Tooltip("Move to start of program"));
        this.setOnAction(e -> moveToEnd(contentView));

    }


    private void moveToEnd(@NotNull ContentView contentView) {

        while(contentView.prevLine()) {}

    }

}

