package com.adiro.asmls.gui.content.codeview;

import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.utilities.ResourceSupplier;


public class LineCounter extends VBox{
    private int numberOfLines;
    public Set<Integer> breakpoints;
    public LineCounter(Set<Integer> breakpoints) {
        super();
        this.numberOfLines = 0;
        this.breakpoints = breakpoints;
        setStyle();
    }

    public void setLineNumber(int newValue) {
        if(newValue > numberOfLines) {
            while(newValue > numberOfLines) {
                numberOfLines++;
                getChildren().add(new LineNumberIndicator(numberOfLines));
            }
            return;
        }

        if(newValue < numberOfLines) {
            while(newValue < numberOfLines) {
                numberOfLines--;
                getChildren().remove(numberOfLines);
            }
        }
    }

    private void setStyle(){
        setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND1, null, null)));
        setBorder(new Border(
                new BorderStroke(
                        Color.DARKGREY,
                        BorderStrokeStyle.SOLID,
                        null,
                        new BorderWidths(0,5,0,0))));
    }

    private void addLines(int numberOfLines){
        for(int i = 1; i<=numberOfLines;i++) {
            getChildren().add(new LineNumberIndicator(i));
        }
    }

    private class LineNumberIndicator extends HBox{
        private final Circle breakPointIndicator;
        private boolean isActive = false;
        public LineNumberIndicator(int lineNumber) {
            super();
            setStyle();

            var lineNumberElement = generateLineNumberElement(lineNumber);
            breakPointIndicator = generateBreakPointIndicator();
            getChildren().addAll(lineNumberElement, breakPointIndicator);

            setOnMouseClicked(e -> {selectBreakPoint();});
        }

        private void setStyle(){
            setPadding(new Insets(2,0,2,0));
            setAlignment(Pos.BASELINE_RIGHT);
        }

        private Text generateLineNumberElement(int lineNumber){
            var lineNumberElement = new Text(Integer.toString(lineNumber));
            Font font = Font.loadFont(ResourceSupplier.Files.Font.CONSOLA, 23);
            lineNumberElement.setFont(font);
            lineNumberElement.setFill(GuiColors.TEXT2);

            return lineNumberElement;
        }

        private Circle generateBreakPointIndicator(){
            var breakPointIndicator = new Circle(7.0, Color.TRANSPARENT);
            HBox.setMargin(breakPointIndicator, new Insets(0, 7, 0, 7));

            return breakPointIndicator;
        }

        private void selectBreakPoint(){
            if(isActive){
                breakPointIndicator.setFill(Color.TRANSPARENT);
                breakpoints.remove(getLineNumber());
            }
            else {
                breakPointIndicator.setFill(GuiColors.BREAKPOINT);
                breakpoints.add(getLineNumber());
            }
            isActive = !isActive;
        }

        private int getLineNumber(){
           Text text = (Text)(getChildren().get(0));
           return Integer.parseInt(text.getText());
        }
    }
}