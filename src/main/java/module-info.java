module com.adiro.asmls {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;


    opens com.adiro.asmls to javafx.fxml;
    exports com.adiro.asmls;
}