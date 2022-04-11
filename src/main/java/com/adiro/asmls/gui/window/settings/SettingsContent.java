package com.adiro.asmls.gui.window.settings;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.adiro.asmls.gui.window.settings.item.Flags;
import com.adiro.asmls.gui.window.settings.item.NumberFormat;


public class SettingsContent extends StackPane {
    public SettingsContent() {
        super();
        var layout = new VBox();
        var numberFormatSetting = new NumberFormat();
        var flagsSettings = new Flags();

        layout.getChildren().addAll(numberFormatSetting, flagsSettings);
        getChildren().add(layout);
    }
}
