package com.adiro.asmls.gui.menubar;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.button.RefreshButton;
import com.adiro.asmls.gui.button.MoveBackButton;
import com.adiro.asmls.gui.button.MoveManyBackButton;
import com.adiro.asmls.gui.button.MoveManyNextButton;
import com.adiro.asmls.gui.button.MoveNextButton;
import com.adiro.asmls.gui.button.MoveToEndButton;
import com.adiro.asmls.gui.button.MoveToStartButton;
import com.adiro.asmls.gui.content.ContentView;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IconMenuBar extends VBox{

    public IconMenuBar(ContentView contentView, Stage stage) {
        super();

        var buttons = new ButtonSet(
                new MoveToStartButton(contentView),
                new MoveManyBackButton(contentView),
                new MoveBackButton(contentView),
                new MoveNextButton(contentView),
                new MoveManyNextButton(contentView),
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
