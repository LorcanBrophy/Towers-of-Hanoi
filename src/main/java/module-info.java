module com.example.tower_of_hanoi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tower_of_hanoi to javafx.fxml;
    exports com.example.tower_of_hanoi;
}