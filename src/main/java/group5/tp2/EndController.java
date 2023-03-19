package group5.tp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EndController implements Initializable {

    @FXML
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Opened");
    }

    @FXML void CloseHandler(ActionEvent e) {
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
