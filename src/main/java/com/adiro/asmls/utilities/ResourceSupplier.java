package com.adiro.asmls.utilities;

import com.adiro.asmls.App;
import javafx.scene.image.ImageView;

public class ResourceSupplier {

    public static class Icons{
        public static ImageView next = getPath("next");
        public static ImageView previous = getPath("previous");
        public static ImageView manyNext = getPath("next-more");
        public static ImageView manyPrevious = getPath("previous-many");
        public static ImageView toEnd = getPath("to-end");
        public static ImageView toBegin = getPath("to-begin");
        public static ImageView nextBreakpoint = getPath("next-breakpoint");
        public static ImageView previousBreakpoint = getPath("previous-breakpoint");
        public static ImageView loading = getPath("loading");
        public static ImageView refresh = getPath("refresh");

        private static ImageView getPath(String iconName){
            return new ImageView(App.class.getResource("icons/" + iconName + ".png").toExternalForm());
        }
    }
}
