package group5.tp2;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    @FXML private Label condLabel;
    @FXML private Label title;
    @FXML private ComboBox condCombo;
    @FXML private  ComboBox train_combo;
    @FXML private ComboBox status_combo;
    // Handling Error messages
    @FXML private Label errorLabel;
    class Training {
        String type;
        int price;
        int duration;
        public Training(String type, int price , int duration) {
            this.duration = duration;
            this.price = price;
            this.type = type;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Const
            String error = "Please enter all your information";
        // Initial
            errorLabel.setText(error);
            condCombo.getItems().add("Early");
            condCombo.getItems().add("To late");

        ArrayList<Training> trainingList = new ArrayList<Training>();
         Training[] data = {new Training("Networking", 3000, 12),
                new Training("Mobile Development", 3500, 9),
                new Training("Software Development", 3000, 12),
                new Training("Web development", 2500, 6),
                new Training("DevOps", 8000, 6)};
        trainingList.addAll(List.of(data));
        for(Training i : trainingList) {
            train_combo.getItems().add(i.type);
        }

        status_combo.getItems().add("Student");
        status_combo.getItems().add("Employee");

    }
}

/*
*
*     // protected void onHelloButtonClick() {
  //      welcomeText.setText("Welcome to JavaFX Application!");
 //   }
*
* */