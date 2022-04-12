package com.adiro.asmls.gui.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


abstract class StyledButton extends Button {
    public StyledButton(){
        super();
        setCustomStyle();
    }

    public StyledButton(String label){
        super(label);
        setCustomStyle();

    }

    public StyledButton(ImageView icon){
        super();
        icon.setPreserveRatio(true);
        icon.setFitHeight(15);
        setGraphic(icon);
        setCustomStyle();
    }

    private void setCustomStyle() {
        setPadding(new Insets(0));
        setSize(30 ,30);
    }

    private void setSize(int width, int height) {
        setMinWidth(width);
        setMaxWidth(width);
        setMinHeight(height);
        setMaxHeight(height);
    }
}

