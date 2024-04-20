package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import javafx.scene.control.TextField;
import tn.esprit.services.ServiceIntern;

import java.awt.*;

public class EditIntern {

    @FXML
    private TextField IdField;

    @FXML
    private TextField passportField;


    @FXML
    private TextField studyField;

    @FXML
    private TextField specialityField;

    @FXML
    private TextField sectorField;

    @FXML
    private TextField proField;

    @FXML
    private TextField latitudeField;

    @FXML
    private TextField longitudeField;

    @FXML
    private TextField imageField;



    private Intern intern;

    ServiceIntern serviceIntern = new ServiceIntern();



    public void setIntern(Intern intern) {
        this.intern = intern;
    }

    @FXML
    private void saveChanges() {
        // Update intern data with values from input fields

        if (intern != null) {

            int originalId = intern.getId();
            int originalUserId = intern.getUserId();

            intern.setCinPassport(passportField.getText());
            intern.setStudylevel(studyField.getText());
            intern.setSpeciality(specialityField.getText());
            intern.setSector(sectorField.getText());
            intern.setProcontact(proField.getText());
            intern.setLatitude(latitudeField.getText());
            intern.setLongitude(longitudeField.getText());
            intern.setProfileimage(imageField.getText());

            intern.setId(originalId);
            intern.setUserId(originalUserId);

            serviceIntern.update(intern);


            closeDialog();
        }
    }
    @FXML
    private void cancel() {
        // Close the dialog without saving changes
        closeDialog();
    }

    private void closeDialog() {
        // Get a reference to the dialog's stage and close it

        Stage stage = (Stage) IdField.getScene().getWindow();
        stage.close();
    }
    public void initData(Intern intern) {
        if (intern != null) {
            // Set the text of each input field with the corresponding intern attribute
            IdField.setText(String.valueOf(intern.getId()));
            IdField.setDisable(true);
            passportField.setText(intern.getCinPassport());
            studyField.setText(intern.getStudylevel());
            specialityField.setText(intern.getSpeciality());
            sectorField.setText(intern.getSector());
            proField.setText(intern.getProcontact());
            latitudeField.setText(intern.getLatitude());
            longitudeField.setText(intern.getLongitude());
            imageField.setText(intern.getProfileimage());

            // Initialize other input fields with corresponding intern attributes
        }
}

}
