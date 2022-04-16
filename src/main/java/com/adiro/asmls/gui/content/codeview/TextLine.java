package com.adiro.asmls.gui.content.codeview;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.utilities.ResourceSupplier;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

class TextLine extends TextFlow {
    private final Text text;

    public TextLine(String line) {
        super();
        text = new Text(line);
        Font font = Font.loadFont(ResourceSupplier.Files.Font.CONSOLA, 23);
        text.setFont(font);
        text.setFill(GuiColors.TEXT);

        getChildren().add(text);
        setPadding(new Insets(0));
        setPadding(new Insets(2,0,2,0));
    }

    String getText() {
        return text.getText();
    }
}