package group5.tp2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

public class CheckoutController {

    @FXML
    Label priceOutput;
    @FXML
    Stage stage;

    @FXML protected void confirmHandler(ActionEvent e) throws IOException {

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

    }
    private static double calcPriceHandler(IndexController.Training trainingField , String state, boolean isSenior) {
        double discount = state == "Student" ? 0.2:  state ==  "Prof" ? 0.1:0;
        double unit_price = trainingField.price * trainingField.duration;
        double price = unit_price - unit_price * discount;
        return isSenior ? 0.95 * price :  price;
    }
    public boolean calculate_price( IndexController.Training trainingField,String state, boolean isSenior, boolean isEarly , LocalDate registrationsDate) {
        boolean rejected  = false;
        double price = calcPriceHandler(trainingField,state,isSenior);
        if(!isEarly) {
            LocalDate startTrainingDate = LocalDate.now();
            int duration  = Period.between(registrationsDate,startTrainingDate).getMonths() + trainingField.duration;
            float pi = (float) ((duration / 3.0)  - 1);
            int newDuration = startTrainingDate.getMonthValue();
            if(  pi >  newDuration ) {
                trainingField.duration = newDuration;
                price =  calcPriceHandler(trainingField,state,isSenior);

            } else {
                price = 0;
                rejected = true;
            }

        }
        priceOutput.setText("$" + price);
        return  rejected;
    }
}
