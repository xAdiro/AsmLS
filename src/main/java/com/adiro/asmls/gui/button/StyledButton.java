package com.adiro.asmls.gui.button;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
abstract class StyledButton extends Button {

    StyledButton(){
        super();
        setCustomStyle();

    }

    StyledButton(String label){
        super(label);
        setCustomStyle();

    }

    StyledButton(ImageView icon){
        super();
        icon.setPreserveRatio(true);
        icon.setFitHeight(15);
        setGraphic(icon);
        setCustomStyle();
    }

    private void setCustomStyle() {
        setPadding(new Insets(0));
        setSize(30 ,30);

//		setBackground(new Background(
//    			new BackgroundFill(
//    					GuiColors.ICONBUTTON, null, null)));

    }

    private void setSize(int width, int height) {

        setMinWidth(width);
        setMaxWidth(width);
        setMinHeight(height);
        setMaxHeight(height);
    }


}

