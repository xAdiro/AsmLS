package com.adiro.asmls.gui.content;

import javafx.geometry.Insets;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import com.adiro.asmls.gui.GuiColors;
import com.adiro.asmls.gui.global_blocks.DoubleText;


public class RegistersView extends VBox{

    private final StandardRegister ax;
    private final StandardRegister bx;
    private final StandardRegister cx;
    private final StandardRegister dx;
    private final FlagsRegister flags;

    public RegistersView() {
        super();
        setBackground(new Background(
                new BackgroundFill(
                        GuiColors.BACKGROUND2, null, null)));

        ax = new StandardRegister(" A");
        bx = new StandardRegister(" B");
        cx = new StandardRegister(" C");
        dx = new StandardRegister(" D");
        flags = new FlagsRegister();

        getChildren().add(ax);
        getChildren().add(new Separator());
        getChildren().add(bx);
        getChildren().add(new Separator());
        getChildren().add(cx);
        getChildren().add(new Separator());
        getChildren().add(dx);
        getChildren().add(new Separator());
        getChildren().add(flags);
    }

    public void setAx(int newValue) {
        ax.setValue(newValue);
    }

    public void setBx(int newValue) {
        bx.setValue(newValue);
    }

    public void setCx(int newValue) {
        cx.setValue(newValue);
    }

    public void setDx(int newValue) {
        dx.setValue(newValue);
    }

    public void setFlags(int newValue) {
        flags.setValue(newValue);
    }

    private class StandardRegister extends HBox{
        int value = 0;
        private final DoubleText rxText;
        private final DoubleText rlText;
        private final DoubleText rhText;

        public StandardRegister(String registerLetter) {
            super();

            var label = registerLetter + "X:";
            rxText = new DoubleText(label, Integer.toString(value), 20);
            rxText.setMinWidth(150);


            label = registerLetter + "L:";
            rlText = new DoubleText(label, Integer.toString(value & 0x00FF), 12);
            var rl = new HBox(rlText);
            rl.setMinWidth(100);

            label = registerLetter + "H:";
            rhText = new DoubleText(label, Integer.toString(value >> 8), 12);
            var rh = new HBox(rhText);
            rh.setMinWidth(100);

            var halves = new VBox(rh, rl);

            getChildren().addAll(rxText, halves);
        }

        public void setValue(int newValue) {
            setNewText(rxText, newValue);
            setNewText(rlText, newValue & 0x00FF);
            setNewText(rhText, newValue >> 8);
        }

        private void setNewText(DoubleText text, int newValue){
            if(!Integer.toString(newValue).equals(text.getValue())){
                text.setValueColor(GuiColors.CHANGE);
            }
            else{
                text.setValueColor(GuiColors.TEXT3);
            }
            text.setValue(newValue);
        }
    }

    private class FlagsRegister extends VBox{
        private final DoubleText[] flags;

        public FlagsRegister() {
            super();

            var flagFontSize = 16;
            flags = new DoubleText[16];
            flags[0] = new DoubleText("CF:", "0", flagFontSize);
            //flags[1] = new DoubleText("1:", "0", flagFontSize);           // consider adding them as an option in future
            flags[2] = new DoubleText("PF:", "0", flagFontSize);
            //flags[3] = new DoubleText("0:", "0", flagFontSize);
            flags[4] = new DoubleText("AF:", "0", flagFontSize);
            //flags[5] = new DoubleText("0:", "0", flagFontSize);
            flags[6] = new DoubleText("ZF:", "0", flagFontSize);
            flags[7] = new DoubleText("SF:", "0", flagFontSize);
            flags[8] = new DoubleText("TF:", "0", flagFontSize);
            flags[9] = new DoubleText("IF:", "0", flagFontSize);
            flags[10] = new DoubleText("DF:", "0", flagFontSize);
            flags[11] = new DoubleText("OF:", "0", flagFontSize);
            flags[12] = new DoubleText("PL:", "0", flagFontSize);
            flags[13] = new DoubleText("IO:", "0", flagFontSize);
            flags[14] = new DoubleText("NT:", "0", flagFontSize);
            //flags[15] = new DoubleText("0:", "0", flagFontSize);

            for(var flag : flags) {
                if(flag != null) {
                    flag.setValueColor(Color.GRAY);
                    getChildren().add(flag);
                }
            }
            VBox.setMargin(this, new Insets(0,0,0,10));
        }

        public void setValue(int newValue) {
            for(int i = 0; i < flags.length; i++) {
                if(flags[i] != null) {
                    setText(flags[i], (newValue >> i) & 1);
                }
            }
        }

        private void setText(DoubleText text, int newValue){
            if(!Integer.toString(newValue).equals(text.getValue())){
                text.setValueColor(GuiColors.CHANGE);
            }
            else{
                if(newValue==1) text.setValueColor(Color.LIME);
                else text.setValueColor(Color.GRAY);
            }
            text.setValue(newValue);
        }
    }
}

