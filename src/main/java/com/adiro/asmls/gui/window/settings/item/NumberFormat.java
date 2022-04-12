package com.adiro.asmls.gui.window.settings.item;

import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;


public class NumberFormat extends SettingsItem{
    public NumberFormat(){
        super();
        var label = new Text("Number Format");
        var options = new ComboBox<String>();
        options.getItems().addAll(
          "Decimal",
          "Binary",
          "Hexadecimal",
          "Octal"
        );
        options.getSelectionModel().selectFirst();

        getChildren().addAll(label, options);
    }
}
