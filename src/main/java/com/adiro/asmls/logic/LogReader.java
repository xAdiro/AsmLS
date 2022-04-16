package com.adiro.asmls.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.adiro.asmls.gui.content.RegistersView;
import com.adiro.asmls.gui.content.codeview.CodeView;
import com.adiro.asmls.utilities.ResourceSupplier;

public class LogReader {
    int currentStep = 0;
    private String filePrefix;

    public LogReader(RegistersView registers, CodeView codeView) {
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

    public byte[] firstLine() throws IOException{
        byte[] debugInfo;
        Path firstFilePath = Paths.get(getFirstFilePath());
        debugInfo = Files.readAllBytes(firstFilePath);

        currentStep = 1;
        return debugInfo;
    }

    public byte[] readLastLine(){
        Path checkedFilePath = Paths.get(getFilePath());
        while(Files.exists(checkedFilePath)){
            currentStep++;
            checkedFilePath = Paths.get(getFilePath());
        }

        currentStep--;  // File doesn't exist so go back where it exists

        try{
            var lastLine = readNextLine();

            if (Files.exists(Paths.get(getFilePath(currentStep-1)))) currentStep++;

            return lastLine;
        }
        catch (IOException e){
            currentStep-=3;
            byte[] lastLine = null;
            try {
                lastLine = readNextLine();
                return readNextLine();
            } catch (IOException e2){/* ignore */}
        }
        return new byte[]{};

    }

    public byte[] readNextLine() throws IOException{
        byte[] debugInfo;
        Path filePath = Paths.get(getFilePath());
        currentStep++;
        try{
            debugInfo = Files.readAllBytes(filePath);
        }
        catch (IOException e){
            if (!Files.exists(Paths.get(getFilePath(currentStep-2)))) currentStep--;
            throw e;
        }

        return debugInfo;
    }

    public byte[] readPrevLine() throws IOException{
        if(currentStep>1) {
            currentStep-=2;
            Path filePath = Paths.get(getFilePath());
            currentStep++;
            return Files.readAllBytes(filePath);
        }
        throw new IOException();
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
}

