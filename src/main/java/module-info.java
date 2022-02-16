module com.adiro.asmls {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.adiro.asmls to javafx.fxml;
    exports com.adiro.asmls;
    exports com.adiro.asmls.gui.content.codeview;
    exports com.adiro.asmls.gui.content;
    exports com.adiro.asmls.gui.menubar.textmenu;
    exports com.adiro.asmls.gui.button;
}