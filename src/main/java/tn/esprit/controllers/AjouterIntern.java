package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;
import javafx.stage.Stage;
import javafx.scene.Scene;


import java.awt.*;
import java.io.IOException;
import org.controlsfx.control.Notifications;


public class AjouterIntern {

    @FXML
    private TableView tab;

    @FXML
    private TextField cinP;

    @FXML
    private TextField studyLeV;

    @FXML
    private TextField special;

    @FXML
    private TextField user_id;

    @FXML
    private TextField sector;

    @FXML
    private TextField procontact;

    @FXML
    private TextField latitude;

    @FXML
    private TextField longitude;

    @FXML
    private TextField profileimage;

    @FXML
    private Button afficher;
    ServiceIntern i = new ServiceIntern();



    @FXML
    void afficherIntern() throws IOException {
        // Load AfficherIntern.fxml

        Parent root = FXMLLoader.load(getClass().getResource("/AfficherIntern.fxml"));
        Stage stage = (Stage) afficher.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void ajouterIntern(ActionEvent event) {
        try {

            if (validateInput()) {

                Intern intern = new Intern();
                intern.setUser_id(Integer.parseInt(user_id.getText()));
                intern.setCin_passport(cinP.getText());
                intern.setStudylevel(studyLeV.getText());
                intern.setSpeciality(special.getText());
                intern.setSector(sector.getText());
                intern.setProcontact(procontact.getText());
                intern.setLatitude(latitude.getText());
                intern.setLongitude(longitude.getText());
                intern.setProfileimage(profileimage.getText());


                i.add(intern);


                clearFields();
                Image img =new Image("/images/check.png");
                Notifications.create()
                        .graphic(new ImageView(img))
                        .title("Ajout Stagiaire")
                        .text("ajout mis avec success.")
                        .hideAfter(Duration.seconds(4))
                        .show();
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid User ID", "Please enter a valid integer for User ID.");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearFields() {
        // Clear all text fields
        user_id.clear();
        cinP.clear();
        studyLeV.clear();
        special.clear();
        sector.clear();
        procontact.clear();
        latitude.clear();
        longitude.clear();
        profileimage.clear();
    }

    private boolean validateInput() {

        if (user_id.getText().isEmpty() || cinP.getText().isEmpty() || studyLeV.getText().isEmpty()
                || special.getText().isEmpty() || sector.getText().isEmpty() || procontact.getText().isEmpty()
                || latitude.getText().isEmpty() || longitude.getText().isEmpty() || profileimage.getText().isEmpty()) {
            showAlert("Error", "Missing Fields", "Please fill in all fields.");
            return false;
        }
    return true;
    }
}

