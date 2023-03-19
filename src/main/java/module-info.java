module group5.tp2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens group5.tp2 to javafx.fxml;
    exports group5.tp2;
}