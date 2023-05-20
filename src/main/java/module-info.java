module com.isi.isi3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.isi.isi3 to javafx.fxml;
    exports com.isi.isi3;
}