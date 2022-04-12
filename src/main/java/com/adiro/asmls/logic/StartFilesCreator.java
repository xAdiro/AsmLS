package com.adiro.asmls.logic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class StartFilesCreator {
    public static void generateFiles(){
        try {
            var sourceFile = new File("./.AsmLS-Config");
            var helloFile = new File(".hello.txt");
            if(sourceFile.createNewFile()) {
                System.out.println("[INFO] Generated asmls directory");
                Files.write(Paths.get(sourceFile.getAbsolutePath()), List.of("./.hello.txt"), StandardCharsets.UTF_8);
            }
            if(helloFile.createNewFile()){
                System.out.println("[INFO] Generated asmls directory");
                var helloContent = "Hello world!\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
                Files.write(Paths.get(helloFile.getAbsolutePath()), List.of(helloContent), StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
