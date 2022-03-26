package com.adiro.asmls.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import com.adiro.asmls.gui.content.RegistersView;
import com.adiro.asmls.gui.content.codeview.CodeView;

public class LogReader {

    RegistersView registers;
    CodeView code;
    int currentStep = 0;
    private String filePrefix;
    private List<Integer> breakPoints;

    public LogReader(RegistersView registers, CodeView sourceArea) {
        this.registers = registers;
        this.code = sourceArea;

        try {
            var resource = ".AsmLS-Config";
            BufferedReader br = new BufferedReader(new FileReader(resource));
            setLogDirectory(new File(br.readLine()).getParent()+"/debug/");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void goToFirstLine(){
        byte[] step = null;
        try {
            Path path = Paths.get(getFileName(0));
            step = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        currentStep = 1;

        code.goToLine(readRegister(step, 0));
        setRegisters(step);
        registers.setFlags(readRegister(step, 9));
    }

    public void goToLastLine(){
        Path checkedPath = null;
        do{
            currentStep++;
            checkedPath = Paths.get(getFileName());
        }while(Files.exists(checkedPath));

        currentStep--;
        nextLine();
        nextLine();
    }

    public boolean nextLine(){

        byte[] step = null;
        try {
            Path path = Paths.get(getFileName());
            step = Files.readAllBytes(path);
        } catch (IOException e) {
            currentStep--;
            Path previousFile = Paths.get(getFileName());
            if(Files.exists(previousFile)){
                currentStep+=2;
                code.goToNextLine();
                code.haltLine(code.getCurrentLine());
            }
            else{
                currentStep++;
            }
            return false;
        }
        currentStep++;

        code.goToLine(readRegister(step, 0));
        setRegisters(step);
        registers.setFlags(readRegister(step, 9));
        return true;
    }

    public boolean prevLine() {

        if(currentStep>1) {
            currentStep -=2;
            nextLine();
            return true;
        }
        return false;
    }

    public void goForwardToBreakPoint(Set<Integer> breakPoints){
        do{
            if(!nextLine()) break;
        }while(!breakPoints.contains(code.getCurrentLine()+1));
    }

    public void goBackwardsToBreakPoint(Set<Integer> breakPoints){

        do{
            if(!prevLine()) break;
        }
        while(!breakPoints.contains(code.getCurrentLine()+1));
    }
    private void setRegisters(byte[] bytes) {

        registers.setAx(readRegister(bytes, 1));
        registers.setBx(readRegister(bytes, 2));
        registers.setCx(readRegister(bytes, 3));
        registers.setDx(readRegister(bytes, 4));
    }

    private int readRegister(byte[] bytes, int n) {

        int value = (int) (ConvertToUnsignedValue(bytes[2*n]) << 8);
        value += ConvertToUnsignedValue(bytes[2*n+1]);
        return value;

    }

    @SuppressWarnings("unused")
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

    private int ConvertToUnsignedValue(byte signedByte) {

        final int bias = 256;

        if(signedByte < 0) {
            return (int)signedByte + bias;
        }
        return (int)signedByte;

    }

    private String getFileName() {
        var fileName = filePrefix + String.format("%05d", currentStep) + "DEB.LOG";
        System.out.println("Debug file: " + fileName);
        return fileName;
    }

    private String getFileName(int fileId){
        var fileName = filePrefix + String.format("%05d", fileId) + "DEB.LOG";
        System.out.println("Debug file: " + fileName);
        return fileName;
    }

    public void setLogDirectory(String location) {
        filePrefix = location;
    }

}

