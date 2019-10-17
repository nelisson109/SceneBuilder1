module Unidad2javaFXejemplo {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    exports com.martin;
    exports com.martin.logica;
    exports com.martin.models;
    exports com.martin.views;

    opens com.martin.views to javafx.fxml;
}