package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.models.Internshiprequest;

import tn.esprit.services.ServiceInternShipRequest;

import java.io.IOException;

public class AjouterRequest {



    @FXML
    private TextField cinFile;

    @FXML
    private TextField cvFile;

    @FXML
    private TextField reclettre;

    @FXML
    private Button afficher,internB;

    ServiceInternShipRequest r = new ServiceInternShipRequest();



    @FXML
    void afficherReq() throws IOException {
        // Load AfficherIntern.fxml

        Parent root = FXMLLoader.load(getClass().getResource("/AfficherRequest.fxml"));
        Stage stage = (Stage) afficher.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void NavigateToIntern() throws IOException {
        // Load AfficherIntern.fxml

        Parent root = FXMLLoader.load(getClass().getResource("/AfficherIntern.fxml"));
        Stage stage = (Stage) internB.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void ajouterReq(ActionEvent event) {
        try {

            if (validateInput()) {

                Internshiprequest internReq = new Internshiprequest();

                internReq.setInternship_id(1);
                internReq.setIntern_id(22);
                internReq.setCinfile(cinFile.getText());
                internReq.setCvfile(cvFile.getText());
                internReq.setReclettrefile(reclettre.getText());



                r.add(internReq);


                clearFields();
                Image img =new Image("/images/check.png");
                Notifications.create()
                        .graphic(new ImageView(img))
                        .title("Ajout Demande De Stage")
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

        cvFile.clear();
        cinFile.clear();
        reclettre.clear();


    }

    private boolean validateInput() {

        if ( cinFile.getText().isEmpty() || cvFile.getText().isEmpty()
                || reclettre.getText().isEmpty()) {
            showAlert("Error", "Missing Fields", "Please fill in all fields.");
            return false;
        }
        return true;
    }
}
