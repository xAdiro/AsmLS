package com.adiro.asmls.utilities;

import java.io.File;
import java.io.InputStream;

import javafx.scene.image.ImageView;

import com.adiro.asmls.App;


public class ResourceSupplier {
    public static class Icons{
        final public static ImageView next = getIcon("next");
        final public static ImageView previous = getIcon("previous");
        final public static ImageView manyNext = getIcon("next-more");
        final public static ImageView manyPrevious = getIcon("previous-many");
        final public static ImageView toEnd = getIcon("to-end");
        final public static ImageView toBegin = getIcon("to-begin");
        final public static ImageView nextBreakpoint = getIcon("next-breakpoint");
        final public static ImageView previousBreakpoint = getIcon("previous-breakpoint");
        final public static ImageView loading = getIcon("loading");
        final public static ImageView refresh = getIcon("refresh");

        private static ImageView getIcon(String iconName){
            return new ImageView(App.class.getResource("icons/" + iconName + ".png").toExternalForm());
        }
    }

    public static class Files{
        public static class Debug{
            public static String pathFromSource(String originalSourceFilePath){
                return getFileDirectory(originalSourceFilePath) + "/debug/runDebug.asm";
            }

            public static String runFilePathFromSource(String originalSourceFilePath){
                return getFileDirectory(originalSourceFilePath) + "/runDebu.bat";
            }

            public static InputStream sourcePath(){
                return App.class.getResourceAsStream("files/debug.asm");
            }

            public static String dirPathFromSource(String originalSourceFilePath){
                return getFileDirectory(originalSourceFilePath) + "/debug";
            }
        }

        public static class Styles{
            public static String main(){
                return App.class.getResource("styles/common-styles.css").toExternalForm();
            }
            public static String scrollBar(){
                return App.class.getResource("styles/scrollbar-style.css").toExternalForm();
            }
        }

        public static class Font{
            public static final String CONSOLA = App.class.getResource("fonts/CONSOLA.TTF").toExternalForm();
        }
    }

    private static String getFileDirectory(String filePath) {
        File sourceFile = new File(filePath);
        return sourceFile.getParent();
    }
}
