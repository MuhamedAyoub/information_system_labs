package com.isi.isi3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;

public class StudentFormApp extends Application {
    private TextField firstNameField;
    private TextField lastNameField;
    private DatePicker birthDateField;
    private TextField placeOfBirthField;
    private TextArea addressField;
    private TextField phoneField;
    private TextField emailField;
    private ComboBox<String> stateComboBox;

    private DatePicker registrationDateField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Information Form");

        // Create the grid pane
        GridPane gridPane = createGridPane();
        addUIControls(gridPane);

        // Create the scene
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add controls to the grid pane
        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        gridPane.add(new Label("First Name:"), 0, 0);
        gridPane.add(firstNameField, 1, 0);

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        gridPane.add(new Label("Last Name:"), 0, 1);
        gridPane.add(lastNameField, 1, 1);

        birthDateField = new DatePicker();
        gridPane.add(new Label("Birth Date:"), 0, 2);
        gridPane.add(birthDateField, 1, 2);

        placeOfBirthField = new TextField();
        placeOfBirthField.setPromptText("Place of Birth");
        gridPane.add(new Label("Place of Birth:"), 0, 3);
        gridPane.add(placeOfBirthField, 1, 3);

        addressField = new TextArea();
        addressField.setPromptText("Address");
        gridPane.add(new Label("Address:"), 0, 4);
        gridPane.add(addressField, 1, 4);

        phoneField = new TextField();
        phoneField.setPromptText("Phone");
        gridPane.add(new Label("Phone:"), 0, 5);
        gridPane.add(phoneField, 1, 5);

        emailField = new TextField();
        emailField.setPromptText("Email");
        gridPane.add(new Label("Email:"), 0, 6);
        gridPane.add(emailField, 1, 6);



        registrationDateField = new DatePicker();
        gridPane.add(new Label("Registration Date:"), 0, 7);
        gridPane.add(registrationDateField, 1, 7);

        stateComboBox = new ComboBox<>();
        stateComboBox.setPromptText("Select State");
        gridPane.add(new Label("State:"), 0, 8);
        gridPane.add(stateComboBox, 1, 8);

        // Load Algeria states from the file
        loadAlgeriaStates();

        Button submitButton = new Button("Submit");
        gridPane.add(submitButton, 1, 9);
        GridPane.setColumnSpan(submitButton, 2);

        // Add event handler for the submit button
        submitButton.setOnAction(event -> {
            if (validateForm()) {
                // Form is valid, perform the desired action (e.g., saving the data)
                displaySuccessMessage();
            } else {
                // Form is invalid, display error messages
                displayErrorMessage();
            }
        });
    }

    private void loadAlgeriaStates() {
        ObservableList<String> algeriaStates = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader("algeria_states.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\(");
                if (parts.length == 2) {
                    String stateName = parts[0].trim();
                    algeriaStates.add(stateName);
                }
            }
        } catch (IOException e) {
            displayErrorAlert("Failed to load Algeria states.");
            return;
        }
        stateComboBox.setItems(algeriaStates);
    }

    public class Student {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String placeOfBirth;
        private String address;
        private String phone;
        private String email;
        private LocalDate registrationDate;
        private String state;

        public Student(String firstName, String lastName, LocalDate birthDate, String placeOfBirth, String address,
                       String phone, String email, LocalDate registrationDate, String state) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthDate = birthDate;
            this.placeOfBirth = placeOfBirth;
            this.address = address;
            this.phone = phone;
            this.email = email;
            this.registrationDate = registrationDate;
            this.state = state;
        }

        @Override
        public String toString() {
            return String.format("First Name: %s%n" +
                            "Last Name: %s%n" +
                            "Birth Date: %s%n" +
                            "Place of Birth: %s%n" +
                            "Address: %s%n" +
                            "Phone: %s%n" +
                            "Email: %s%n" +
                            "Registration Date: %s%n" +
                            "State: %s%n",
                    firstName, lastName, birthDate, placeOfBirth, address, phone, email, registrationDate, state);
        }

        // Getters and setters for the state field
        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
    private boolean validateForm() {
        boolean isValid = true;
        StringBuilder errorMessage = new StringBuilder();

        if (firstNameField.getText().isEmpty()) {
            errorMessage.append("First Name is required.\n");
            isValid = false;
        }

        if (lastNameField.getText().isEmpty()) {
            errorMessage.append("Last Name is required.\n");
            isValid = false;
        }

        if (birthDateField.getValue() == null) {
            errorMessage.append("Birth Date is required.\n");
            isValid = false;
        }

        if (placeOfBirthField.getText().isEmpty()) {
            errorMessage.append("Place of Birth is required.\n");
            isValid = false;
        }

        if (addressField.getText().isEmpty()) {
            errorMessage.append("Address is required.\n");
            isValid = false;
        }

        if (phoneField.getText().isEmpty()) {
            errorMessage.append("Phone is required.\n");
            isValid = false;
        } else if (!phoneField.getText().matches("\\d{10}")) {
            errorMessage.append("Phone number must be 10 digits.\n");
            isValid = false;
        }

        if (emailField.getText().isEmpty()) {
            errorMessage.append("Email is required.\n");
            isValid = false;
        } else if (!emailField.getText().matches("[\\w.-]+@[\\w.-]+\\.[\\w]{2,4}")) {
            errorMessage.append("Invalid email format.\n");
            isValid = false;
        }
        if (isStudentExists(emailField.getText())) {
            errorMessage.append("A student with the same email already exists.\n");
            isValid = false;
        }
        if (registrationDateField.getValue() == null) {
            errorMessage.append("Registration Date is required.\n");
            isValid = false;
        }

        if (!isValid) {
            displayErrorAlert(errorMessage.toString());
        }

        return isValid;
    }
    private boolean isStudentExists(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the email
                if (line.contains("Email: " + email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            displayErrorAlert("Failed to read student information from file.");
            return false;
        }
        return false;
    }
    private void displaySuccessMessage() {

        Student student = new Student(
                firstNameField.getText(),
                lastNameField.getText(),
                birthDateField.getValue(),
                placeOfBirthField.getText(),
                addressField.getText(),
                phoneField.getText(),
                emailField.getText(),
                registrationDateField.getValue(),
                stateComboBox.getValue()

        );

        // Write the student information to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
            writer.write(student.toString());
            writer.newLine();
        } catch (IOException e) {
            displayErrorAlert("Failed to write student information to file.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Form submitted successfully!");

        // Clear the form fields
        firstNameField.clear();
        lastNameField.clear();
        birthDateField.setValue(null);
        placeOfBirthField.clear();
        addressField.clear();
        phoneField.clear();
        emailField.clear();
        registrationDateField.setValue(null);

        alert.showAndWait();
    }


    private void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please correct the errors in the form.");
        alert.showAndWait();
    }

    private void displayErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();



    }
}
