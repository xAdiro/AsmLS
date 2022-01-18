package com.adiro.nasm_ide;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogReader {
	
	RegistersView registers;
	SourceViewArea sourceArea;
	int currentStep = 0;
	
	public LogReader(RegistersView registers, SourceViewArea sourceArea) {
		this.registers = registers;
		this.sourceArea = sourceArea;
	}
	
	public boolean NextLine(){
		
		Path path = Paths.get(getFileName());
		
		byte[] step = null;
		try {
			step = Files.readAllBytes(path);
		} catch (IOException e) {
			sourceArea.haltCode();
			return false;
		}
		
		if(!isLabel(step)) {
			setRegisters(step);
			var label = new String(step, StandardCharsets.UTF_8);
			sourceArea.NextLine();
			
		}
		else {
			var label = new String(step, StandardCharsets.UTF_8);
			label = label.replaceAll("&","");
			sourceArea.setLine(label);
			System.out.print("label: " + label + "\n");
		}
		
		currentStep++;
		
		
		return true;
	}
	
	private void setRegisters(byte[] bytes) {
		
		
		registers.setAx(calculateRegister(bytes, 0));
		registers.setBx(calculateRegister(bytes, 1));
		registers.setCx(calculateRegister(bytes, 2));
		registers.setDx(calculateRegister(bytes, 3));
		
		//printRegisters(bytes);
		
		
		
	}
	
	private int calculateRegister(byte[] bytes, int n) {
		
		
		int value = (int) (Math.pow(2, 8) * ToUByte(bytes[2*n]));
		value += ToUByte(bytes[2*n+1]);
		return value;
	}
	
	private int ToUByte(byte input) {
		if(input < 0) {
			return (int)input + 256;
		}
		return (int)input;
	}
	
	private void printRegisters(byte[] bytes) {
		for(var register : bytes) {
			if(register < 0) {
				System.out.println(register+256);
			}
			else {
				System.out.println(register);
			}
		}
	}
	
	private String getFileName() {
		final String prefix = "/home/adrian/Dokumenty/Dos/ide/";
		return prefix + String.format("%04d", currentStep) + "DEBU.LOG";
	}
	
	

	private boolean isLabel(byte[] bytes) {
		if(bytes.length == 8) {
			return true;
		}
		else
			return false;
		
	}
}
