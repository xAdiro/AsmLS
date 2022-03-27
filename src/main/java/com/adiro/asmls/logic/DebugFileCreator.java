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

public final class DebugFileCreator {

    private static void createDebugFile(String sourceFilePath) {
        final var debugFilePath = ResourceSupplier.Files.Debug.path(sourceFilePath);

        StringBuilder outputContent = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(sourceFilePath));
            String line = reader.readLine();
            for(int i = 0 ; line != null; line = reader.readLine()) {
                if(!line.isBlank()) {
                    outputContent.append(CodeSupplier.Asm.getDebugLine(line, i));
                    i++;
                }
                outputContent.append(line);
            }

            outputContent.append(CodeSupplier.Asm.LIBRARY_LINE);
            reader.close();
        }
        catch(IOException e) {
            System.out.print("[FileCreator ERROR] couldn't find: " + sourceFilePath);
            e.printStackTrace();
        }

        saveToDebugFile(outputContent.toString(), sourceFilePath);
        System.out.println("[INFO] Generated debug file: " + debugFilePath);
    }


    private static void copyDebugLibrary(String sourceDirPath) {
        var input = ResourceSupplier.Files.Debug.sourcePath();
        Path output = Paths.get(sourceDirPath + "/debug.asm");

        try {
            Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {e.printStackTrace();}
    }

    public static void createRunFile(String sourceFilePath) {
        var debugDirPath = ResourceSupplier.Files.Debug.dirPath(sourceFilePath);
        new File(debugDirPath).mkdirs();
        createDebugFile(sourceFilePath);
        copyDebugLibrary(debugDirPath);

        List<String> runFileContent = List.of(CodeSupplier.Bat.RUNNABLE);
        Path runFilePath = Paths.get(ResourceSupplier.Files.Debug.runFilePath(sourceFilePath));
        try {
            Files.write(runFilePath, runFileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {e.printStackTrace();}
    }

    private static void saveToDebugFile(String content, String sourceFilePath){
        var debugFilePath = ResourceSupplier.Files.Debug.path(sourceFilePath);
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
}

