module org.example.login {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    exports org.example;
    exports org.example.controller;
    exports org.example.model;

    opens org.example.controller to javafx.fxml;
}
