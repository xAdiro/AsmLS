package com.adiro.asmls.logic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class StartFilesCreator {
    public static void generateFiles(){
        try {
            var sourceFile = new File("./.source");
            var helloFile = new File(".hello.txt");
            if(sourceFile.createNewFile()) {
                System.out.println("[INFO] Generated asmls directory");
                Files.write(Paths.get(sourceFile.getAbsolutePath()), Arrays.asList("./.hello.txt"), StandardCharsets.UTF_8);
            }
            if(helloFile.createNewFile()){
                System.out.println("[INFO] Generated asmls directory");
                var helloContent = "Hello world!\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
                Files.write(Paths.get(helloFile.getAbsolutePath()), Arrays.asList(helloContent), StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
