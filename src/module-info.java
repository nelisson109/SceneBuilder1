module Unidad2javaFXejemplo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    exports com.martin;
    exports com.martin.logica;
    exports com.martin.models;
    exports com.martin.views;
    exports com.martin.Utils;

    opens com.martin.views to javafx.fxml;
}