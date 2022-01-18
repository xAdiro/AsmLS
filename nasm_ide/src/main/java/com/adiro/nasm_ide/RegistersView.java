package com.adiro.nasm_ide;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

public class RegistersView extends VBox{
	
	private HBox ax;
	private HBox bx;
	private HBox cx;
	private HBox dx;

	public RegistersView() {
		super();
		
		var axText1 = new Text("AX: ");
		axText1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		var axText2 = new Text("0");
		axText2.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		ax = new HBox(axText1, axText2);
		
		
		var bxText1 = new Text("BX: ");
		bxText1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		var bxText2 = new Text("0");
		bxText2.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		bx = new HBox(bxText1, bxText2);
		
		var cxText1 = new Text("CX: ");
		cxText1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		var cxText2 = new Text("0");
		cxText2.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		cx = new HBox(cxText1, cxText2);
		
		var dxText1 = new Text("DX: ");
		dxText1.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		var dxText2 = new Text("0");
		dxText2.setFont(Font.font("Arial", FontPosture.REGULAR, 20));
		dx = new HBox(dxText1, dxText2);
		
		this.getChildren().add(ax);
		this.getChildren().add(bx);
		this.getChildren().add(cx);
		this.getChildren().add(dx);
	}
	
	public void setAx(int newValue) {
		((Text) ax.getChildren().get(1)).setText(Integer.toString(newValue));
	}
	
	public void setBx(int newValue) {
		((Text) bx.getChildren().get(1)).setText(Integer.toString(newValue));
	}
	
	public void setCx(int newValue) {
		((Text) cx.getChildren().get(1)).setText(Integer.toString(newValue));
	}
	
	public void setDx(int newValue) {
		((Text) dx.getChildren().get(1)).setText(Integer.toString(newValue));
	}
}
