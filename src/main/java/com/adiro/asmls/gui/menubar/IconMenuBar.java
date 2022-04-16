package com.adiro.asmls.gui.menubar;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.button.*;
import com.adiro.asmls.gui.content.CodeReader;


public class IconMenuBar extends VBox{
    public IconMenuBar(CodeReader contentView, Stage stage) {
        super();
        getStyleClass().add("icon-menu-bar");
        var buttons = new ButtonSet(
                new MoveToStartButton(contentView),
                new MoveBackwardsToBreakPointButton(contentView),
                new MoveManyBackButton(contentView),
                new MoveBackButton(contentView),
                new MoveNextButton(contentView),
                new MoveManyNextButton(contentView),
                new MoveForwardToBreakPointButton(contentView),
                new MoveToEndButton(contentView),
                new RefreshButton(contentView)
        );
        getChildren().addAll(buttons);

        setPadding(new Insets(5));
        setBackground(
                new Background(
                        new BackgroundFill(
                                GuiColors.BACKGROUND2,null,null)));
    }

    private class ButtonSet extends HBox{
        public ButtonSet(Button... buttons){
            super(buttons);
            setSpacing(5);
        }
    }
}
