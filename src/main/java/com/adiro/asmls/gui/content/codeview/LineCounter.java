package com.adiro.asmls.gui.content.codeview;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.content.ContentView;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class LineCounter extends VBox{
    private int numberOfLines;
    private ContentView contentView;

    public LineCounter(int numberOfLines, ContentView contentView) {
        super();
        this.contentView = contentView;
        setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND1, null, null)));

        for(int i = 1; i<=numberOfLines;i++) {
            getChildren().add(new LineText(i));
        }
        this.numberOfLines = numberOfLines;


        setBorder(new Border(
                new BorderStroke(
                        Color.DARKGREY,
                        BorderStrokeStyle.SOLID,
                        null,
                        new BorderWidths(0,5,0,0))));
    }

    public void setLineNumber(int newValue) {
        if(newValue > numberOfLines) {
            while(newValue > numberOfLines) {
                numberOfLines++;
                getChildren().add(new LineText(numberOfLines));
            }
            return;
        }

        if(newValue < numberOfLines) {
            while(newValue < numberOfLines) {
                numberOfLines--;
                getChildren().remove(numberOfLines);
            }
            return;
        }
    }

    private class LineText extends HBox{
        Circle breakPointIndicator;
        private boolean isActive = false;
        public LineText(int number) {
            super();
            var lineNumber = new Text(Integer.toString(number));
            lineNumber.setFont(Font.font("Noto Sans Mono", FontPosture.REGULAR, 20));
            lineNumber.setFill(GuiColors.TEXT2);

            breakPointIndicator = new Circle(7.0, Color.TRANSPARENT);
            setMargin(breakPointIndicator, new Insets(0, 7, 0, 7));

            setAlignment(Pos.BASELINE_RIGHT);
            setOnMouseClicked(e -> {selectBreakPoint();});
            getChildren().addAll(lineNumber, breakPointIndicator);
        }

        public void selectBreakPoint(){
            if(isActive){
                breakPointIndicator.setFill(Color.TRANSPARENT);
                contentView.removeBreakPoint(getLineNumber());
            }
            else {
                breakPointIndicator.setFill(GuiColors.BREAKPOINT);
                contentView.addBreakPoint(getLineNumber());
            }
            isActive = !isActive;
        }

        private int getLineNumber(){
           Text text = (Text)(getChildren().get(0));
           return Integer.parseInt(text.getText());
        }
    }
}

