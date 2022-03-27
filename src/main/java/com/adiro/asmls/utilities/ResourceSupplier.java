package com.adiro.asmls.utilities;

import com.adiro.asmls.App;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.InputStream;

public class ResourceSupplier {

    public static class Icons{
        final public static ImageView next = getPath("next");
        final public static ImageView previous = getPath("previous");
        final public static ImageView manyNext = getPath("next-more");
        final public static ImageView manyPrevious = getPath("previous-many");
        final public static ImageView toEnd = getPath("to-end");
        final public static ImageView toBegin = getPath("to-begin");
        final public static ImageView nextBreakpoint = getPath("next-breakpoint");
        final public static ImageView previousBreakpoint = getPath("previous-breakpoint");
        final public static ImageView loading = getPath("loading");
        final public static ImageView refresh = getPath("refresh");

        private static ImageView getPath(String iconName){
            return new ImageView(App.class.getResource("icons/" + iconName + ".png").toExternalForm());
        }
    }

    public static class Files{
        public static class Debug{
            public static String path(String sourceFilePath){ //Path is relative to source file path
                return getFileDirectory(sourceFilePath) + "/debug/runDebug.asm";
            }
            public static String runFilePath(String sourceFilePath){
                return getFileDirectory(sourceFilePath) + "/runDebu.bat";
            }
            public static InputStream sourcePath(){
                return App.class.getResourceAsStream("files/debug.asm");
            }
            public static String dirPath(String sourceFilePath){
                return getFileDirectory(sourceFilePath) + "/debug";
            }
        }
    }

    private static String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }
}
