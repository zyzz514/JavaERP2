package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import javafx.scene.control.TextField;

import java.awt.*;

public class EditIntern {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passportField;

    // Other input fields for intern attributes

    private Intern intern;

    public void initialize() {
        // Initialize the dialog with intern data
        if (intern != null) {
            userIdField.setText(String.valueOf(intern.getUserId()));
            passportField.setText(intern.getCinPassport());
            // Initialize other input fields with intern data
        }
    }

    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    @FXML
    private void saveChanges() {
        // Update intern data with values from input fields
        intern.setUserId(Integer.parseInt(userIdField.getText()));
        intern.setCinPassport(passportField.getText());
        // Update other intern attributes

        // Call a method to update the intern data in the database
        // serviceIntern.update(intern);

        // Close the dialog
        closeDialog();
    }

    @FXML
    private void cancel() {
        // Close the dialog without saving changes
        closeDialog();
    }

    private void closeDialog() {
        // Get a reference to the dialog's stage and close it
        Stage stage = (Stage) userIdField.getScene().getWindow();
        stage.close();
    }
    public void initData(Intern intern) {
        this.intern = intern;
        // Initialize input fields with intern data
        userIdField.setText(Integer.toString(intern.getUserId()));
        passportField.setText(intern.getCinPassport());
        // Initialize other input fields with corresponding intern attributes
    }
}
