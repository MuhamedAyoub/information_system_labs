package group5.tp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class IndexController implements Initializable {
    String errorMessage = "Please enter all your information";
    Training[] data = {new Training("Networking", 3000, 12),
            new Training("Mobile Development", 3500, 9),
            new Training("Software Development", 3000, 12),
            new Training("Web development", 2500, 6),
            new Training("DevOps", 8000, 6)};
    ArrayList<Training> trainingList = new ArrayList<Training>(List.of(data));

    @FXML
    Parent root,root2;
    @FXML
    Stage stage;
    @FXML
    Scene scene;
    @FXML private Label errorLabel,condLabel,title;
    @FXML protected ComboBox<String> status_combo;
    @FXML protected ComboBox<String> train_combo;
    @FXML protected ComboBox<String> condCombo;
    // Handling Error messages
    @FXML private Button resetbtn,submitbtn;
    @FXML private TextField fname,lname;
    @FXML private DatePicker idate;
    @FXML private RadioButton yes,no;
    public static class Training {
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
        // Initial

            condCombo.getItems().add("Early");
            condCombo.getItems().add("To late");


        for(Training i : trainingList) {
            train_combo.getItems().add(i.type);
        }

        status_combo.getItems().add("Student");
        status_combo.getItems().add("Employee");
        status_combo.getItems().add("Prof");

        no.setSelected(true);

    }
    public  int search(String name) {
        int i = 0;
        while ( i < trainingList.toArray().length && trainingList.get(i).type != name ) {
                i++;
            }
        return trainingList.get(i).type == name ? i:-1;
    }
    // Events
     @FXML  LocalDate getDate() {
        return LocalDate.of(idate.getValue().getYear(), idate.getValue().getMonth(),idate.getValue().getDayOfMonth());
    }
    @FXML protected  void resetHandler(ActionEvent e) {
        fname.setText("");
        lname.setText("");
        idate.setValue(null);
    }
    @FXML protected void submitHandler(ActionEvent event ) throws IOException {
        if(fname.getText().length() == 0 || lname.getText().length() == 0 || idate.getValue() == null) {
            errorLabel.setText(errorMessage);
        }else {
            errorLabel.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout.fxml"));
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("end.fxml"));

            root = loader.load();
            root2 = loader2.load();

            boolean isEarly = Objects.equals(condCombo.getValue(), "Early");

            // Handling Next Logic
            // Checkout
            CheckoutController controller = loader.getController();
           boolean status =  controller.calculate_price(trainingList.get(search(train_combo.getValue())
            ), status_combo.getValue(), yes.isSelected(), isEarly, getDate());
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            if(!status) {
                scene = new Scene(root);
            } else {
                scene = new Scene(root2);
            }
            stage.setScene(scene);
            stage.show();

        }

    }
}

/*
*
*     // protected void onHelloButtonClick() {
  //      welcomeText.setText("Welcome to JavaFX Application!");
 //   }
*
* */