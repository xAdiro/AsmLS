package com.adiro.asmls.gui.window.settings;

import com.adiro.asmls.gui.window.settings.item.NumberFormat;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class SettingsContent extends StackPane {

    public SettingsContent() {
        super();
        var layout = new VBox();

        var numberFormatSetting = new NumberFormat();
        layout.getChildren().add(numberFormatSetting);
        getChildren().add(layout);

    }
}
