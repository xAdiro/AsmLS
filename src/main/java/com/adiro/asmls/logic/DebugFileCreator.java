package com.adiro.asmls.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.adiro.asmls.utilities.CodeSupplier;
import com.adiro.asmls.utilities.ResourceSupplier;

public class DebugFileCreator {
    private final String originalSourceFilePath;
    private final String originalSourceDirPath;
    public DebugFileCreator(String originalSourceFilePath){
        this.originalSourceFilePath = originalSourceFilePath;
        this.originalSourceDirPath = ResourceSupplier.Files.Debug.dirPathFromSource(originalSourceFilePath);
    }


    public void createRunFile() {
        var debugDirPath = ResourceSupplier.Files.Debug.dirPathFromSource(originalSourceFilePath);
        new File(debugDirPath).mkdirs();
        createDebugFile();
        copyDebugLibrary();

        List<String> runFileContent = List.of(CodeSupplier.Bat.RUNNABLE);
        Path runFilePath = Paths.get(ResourceSupplier.Files.Debug.runFilePathFromSource(originalSourceFilePath));
        try {
            Files.write(runFilePath, runFileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {e.printStackTrace();}
    }


    private void createDebugFile() {
        final var debugFilePath = ResourceSupplier.Files.Debug.pathFromSource(originalSourceFilePath);

        StringBuilder outputContent = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(originalSourceFilePath));
            String line = reader.readLine();
            for(int i = 0 ; line != null; line = reader.readLine()) {
                if(!line.isBlank()) {
                    line = (CodeSupplier.Asm.getDebugLine(line, i));
                    i++;
                }
                outputContent.append(line);
            }

            outputContent.append(CodeSupplier.Asm.LIBRARY_LINE);
            reader.close();
        }
        catch(IOException e) {
            System.out.print("[FileCreator ERROR] couldn't find: " + originalSourceFilePath);
            e.printStackTrace();
        }

        saveToDebugFile(outputContent.toString());
        System.out.println("[INFO] Generated debug file: " + debugFilePath);
    }

    private void saveToDebugFile(String content){
        var debugFilePath = ResourceSupplier.Files.Debug.pathFromSource(originalSourceFilePath);
        try {
            new File(debugFilePath).createNewFile();

            var writer = new BufferedWriter(new FileWriter(debugFilePath));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.print("[FileCreator ERROR] couldn't write to: " + debugFilePath);
            e.printStackTrace();
        }
    }

    private void copyDebugLibrary() {
        var input = ResourceSupplier.Files.Debug.sourcePath();
        Path output = Paths.get(originalSourceDirPath + "/debug.asm");

        try {
            Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {e.printStackTrace();}
    }
}

