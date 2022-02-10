package com.adiro.asmls.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.adiro.asmls.App;

public final class DebugFileCreator {

    private static void createDebugFile(String sourceFilePath) {
        var outputContent = "";
        var debugFilePath = getFileDirectory(sourceFilePath) + "/debug/runDebug.asm";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(sourceFilePath));


            String line = reader.readLine();
            int i = 0;
            while(line != null) {

                if(!line.isBlank()) {
                    if(!isMemoryLine(line)) {
                        line = fixLoop(line);


                        outputContent +=
                                line	+ "\n"
                                        + ";<DEBUG>\n"
                                        + MessageFormat.format("mov	[dane], byte {0}", (i >> 8)) + "\n"
                                        + MessageFormat.format("mov	[dane+1], byte {0}", (i & 0x00FF)) + "\n"
                                        + "call 	zapLinie\n"
                                        + ";</DEBUG>\n";
                    }
                    else {
                        line = line+"\t;MEMORY\n";
                        outputContent += line;
                    }
                    i++;
                }

                else {
                    outputContent += line + "\n";
                }
                line = reader.readLine();


            }

            outputContent += "\n%include 'debug.asm'";

            reader.close();
        }
        catch(IOException e) {
            System.out.print("[FileCreator ERROR] couldn't find: " + sourceFilePath);
            e.printStackTrace();
        }


        try {

            var file = new File(debugFilePath);
            file.createNewFile();

            var writer = new BufferedWriter(new FileWriter(debugFilePath));
            writer.write(outputContent);
            writer.close();
        } catch (IOException e) {
            System.out.print("[FileCreator ERROR] couldn't write to: " + debugFilePath);
            e.printStackTrace();
        }

        System.out.println("[INFO] Generated debug file: " + debugFilePath);
    }

    private static void copyDebugLibrary(String sourceDirPath) {

        var input = App.class.getResourceAsStream("files/debug.asm");
        Path output = Paths.get(sourceDirPath + "/debug/debug.asm");

        try {
            Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createRunFile(String sourceFilePath) {
        var sourceDirPath = getFileDirectory(sourceFilePath);
        new File(sourceDirPath + "/debug").mkdirs();
        createDebugFile(sourceFilePath);
        copyDebugLibrary(sourceDirPath);

        List<String> runFileContent =
                Arrays.asList(
                        "cd debug\n"
                                + "a:\\nasm\\nasm -o program.com -f bin runDebug.asm\n"
                                + "del *.log\n"
                                + "program.com\n"
                                + "cd ..");

        Path runFilePath = Paths.get(sourceDirPath+"/debug.bat");
        try {
            Files.write(runFilePath, runFileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String fixLoop(String line) {

        String regexLoop = "loop.*";
        var loopPattern = Pattern.compile(regexLoop);
        var matcher = loopPattern.matcher(line.trim());
        if(matcher.matches()) {

            String label = line.trim().substring(line.trim().indexOf("loop") + 4);


            line = "dec cx\n"
                    + "cmp cx, 0\n"
                    + "jnz "
                    + label + "\n";
        }
        return line;

    }

    private static boolean isMemoryLine(String line) {


        //String regexMemoryLine = "[\t, ]*(([^(\t| )]*[\t, ]+)|([\t, ]*))db[\t, ]+";
        String regexMemoryLine =".*[\t, ]+(db|dw|dd|dq|dt)[\t, ]+.*";
        var memoryLinePattern = Pattern.compile(regexMemoryLine);
        var matcher = memoryLinePattern.matcher(line);

        if(matcher.matches()) return true;
        return false;
    }

    private static String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }

}

