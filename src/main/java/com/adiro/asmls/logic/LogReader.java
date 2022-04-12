package com.adiro.asmls.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import com.adiro.asmls.gui.content.RegistersView;
import com.adiro.asmls.gui.content.codeview.CodeView;
import com.adiro.asmls.utilities.ResourceSupplier;

public class LogReader {
    RegistersManager registers;
    CodeView sourceArea;
    int currentStep = 0;
    private String filePrefix;

    public LogReader(RegistersView registers, CodeView sourceArea) {
        this.registers = new RegistersManager(registers);
        this.sourceArea = sourceArea;

        try {
            var prevConfigFile = ".AsmLS-Config";
            BufferedReader reader = new BufferedReader(new FileReader(prevConfigFile));
            var prevOriginalFilePath = reader.readLine();

            setLogDirectory(ResourceSupplier.Files.Debug.dirPathFromSource(prevOriginalFilePath));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogDirectory(String location) {
        filePrefix = location + "/";
    }

    public void goToFirstLine(){
        byte[] debugInfo;
        try {
            Path firstFilePath = Paths.get(getFirstFilePath());
            debugInfo = Files.readAllBytes(firstFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        sourceArea.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);

        currentStep = 1;
    }

    public void goToLastLine(){
        Path checkedFilePath;
        do {
            currentStep++;
            checkedFilePath = Paths.get(getFilePath());
        } while(Files.exists(checkedFilePath));

        currentStep--;  // File doesn't exist so go back where it exists
        nextLine();
        nextLine();
    }

    public boolean nextLine(){
        byte[] debugInfo = null;
        try {
            Path filePath = Paths.get(getFilePath());
            debugInfo = Files.readAllBytes(filePath);
        } catch (IOException e) {
            if(!isAlreadyHalted()){
                haltLine();
            }
            return false; // no next line
        }

        sourceArea.goToLine(registers.readLineNumber(debugInfo));
        registers.set(debugInfo);

        currentStep++;
        return true;
    }

    public boolean prevLine() {
        if(currentStep>1) {
            currentStep -=2; // 2 steps backwards to use nextLine() -2 + 1 = -1
            nextLine();
            return true;
        }
        return false;
    }

    public void goForwardToBreakPoint(Set<Integer> breakPoints){
        do {
            var doesNextLineExists = nextLine();
            if(!doesNextLineExists) break;
        } while(!breakPoints.contains(sourceArea.getCurrentLine()+1));
    }

    public void goBackwardsToBreakPoint(Set<Integer> breakPoints){
        do {
            var doesPrevLineExists = prevLine();
            if(!doesPrevLineExists) break;
        } while(!breakPoints.contains(sourceArea.getCurrentLine()+1));
    }

    private String getFirstFilePath(){
        return getFilePath(0);
    }

    private String getFilePath() {
        return getFilePath(currentStep);
    }

    private String getFilePath(int fileId){
        var fileName = filePrefix + String.format("%05d", fileId) + "DEB.LOG";
        System.out.println("Debug file: " + fileName);
        return fileName;
    }

    private String getPrevFilePath(){
        return getFilePath(currentStep -1);
    }

    private boolean isAlreadyHalted(){
        var prevFile = Paths.get(getPrevFilePath());
        return !Files.exists(prevFile);
    }

    private void haltLine(){
        currentStep++;
        sourceArea.goToNextLine();
        sourceArea.haltCurrentLine(sourceArea.getCurrentLine());
    }
}

