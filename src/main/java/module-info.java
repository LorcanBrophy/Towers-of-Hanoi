module com.example.gui_practice {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gui_practice to javafx.fxml;
    exports com.example.gui_practice;
}