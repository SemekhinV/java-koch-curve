module com.farct.fractals {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.fractals.fractals to javafx.fxml;
    exports com.fractals.fractals;
    exports com.fractals.fractals.controller;
    opens com.fractals.fractals.controller to javafx.fxml;
    exports com.fractals.fractals.model;
    opens com.fractals.fractals.model to javafx.fxml;
}