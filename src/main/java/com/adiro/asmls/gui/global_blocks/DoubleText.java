package com.adiro.asmls.gui.global_blocks;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DoubleText extends HBox{
    private final Text value;
    public DoubleText(String label, String value, int fontSize) {
        super();
        var labelText = new Text(label);
        labelText.setFont(Font.font(fontSize));
        labelText.getStyleClass().add("text");
        var labelBox = new HBox(labelText);
        labelBox.setMinWidth(fontSize*2.5);
        getChildren().add(labelBox);


        this.value = new Text(value);
        this.value.setFont(Font.font(fontSize));
        this.value.getStyleClass().add("value");
        getChildren().add(this.value);

    }
    public void setValue(int newValue) {
        value.setText(Integer.toString(newValue));
    }
    public String getValue() {return value.getText();}
    public void setValueColor(Color color){ value.setFill(color);
    }


}

