package com.adiro.asmls.gui.window.settings.item;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;


public class Flags extends SettingsItem{
    public Flags(){
        super();
        getChildren().addAll(new Text("Flags displayed:"),
             new CheckBox("CF"),
             new CheckBox("1"),
             new CheckBox("PF"),
             new CheckBox("0"),
             new CheckBox("AF"),
             new CheckBox("0"),
             new CheckBox("ZF"),
             new CheckBox("SF"),
             new CheckBox("TF"),
             new CheckBox("IF"),
             new CheckBox("DF"),
             new CheckBox("OF"),
             new CheckBox("PL"),
             new CheckBox("IO"),
             new CheckBox("NT"),
             new CheckBox("0")
        );
    }
}
