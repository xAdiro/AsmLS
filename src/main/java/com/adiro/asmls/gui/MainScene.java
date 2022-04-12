package com.adiro.asmls.gui;

import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScene extends Scene {
    public MainScene(Parent parent) {
        super(parent, 800, 600);
        getStylesheets().add(ResourceSupplier.Files.Styles.main());
        getStylesheets().add(ResourceSupplier.Files.Styles.scrollBar());
    }

}
