package tn.esprit.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FrontOffice {

    @FXML
    private TextField searchIN;
    private ServiceIntern serviceIntern = new ServiceIntern();

    @FXML
    private Button ajouter;



    @FXML
    private HBox cardContainer;

    private Pane createCard(Intern intern) {
        // Create a Pane or any suitable container for your card
        Pane card = new Pane();
        card.getStyleClass().add("card"); // Add CSS class for styling
        card.setPrefSize(220, 200); // Set the preferred size of the card

        // Create and configure the content of the card
        Label passportLabel = new Label("Passport: " + intern.getCinPassport());
        passportLabel.setLayoutX(10);
        passportLabel.setLayoutY(10);

        Label studyLevelLabel = new Label("Study Level: " + intern.getStudylevel());
        studyLevelLabel.setLayoutX(10);
        studyLevelLabel.setLayoutY(30);

        Label specialityLabel = new Label("Speciality: " + intern.getSpeciality());
        specialityLabel.setLayoutX(10);
        specialityLabel.setLayoutY(50);

        Label SectorLabel = new Label("Sector: " + intern.getSector());
        SectorLabel.setLayoutX(10);
        SectorLabel.setLayoutY(70);

        Label ContactLabel = new Label("Contact: " + intern.getProcontact());
        ContactLabel.setLayoutX(10);
        ContactLabel.setLayoutY(90);

        Label ImgLabel = new Label("Image: " + intern.getProfileimage());
        ImgLabel.setLayoutX(10);
        ImgLabel.setLayoutY(110);


        card.getChildren().addAll(passportLabel, studyLevelLabel, specialityLabel, SectorLabel, ContactLabel, ImgLabel);


        return card;
    }

    @FXML
    void ajouterIntern() throws IOException {
        // Load SecondPage.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/AjouterIntern.fxml"));
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void displayInterns2(List<Intern> interns) {

        cardContainer.getChildren().clear(); // Clear previous content

        for (Intern intern : interns) {
            Pane card = createCard(intern);
            // Add the card to the container
            cardContainer.getChildren().add(card);
            }


        }

    public void initialize() {
        displayInterns2(serviceIntern.getAll());

    }



    @FXML
    void searchIntern() {
        String searchText = searchIN.getText().trim();
        if (searchText.isEmpty()) {
            displayInterns2(serviceIntern.getAll()); // Show all interns if search text is empty
        } else {
            List<Intern> searchResults = new ArrayList<>();
            for (Intern intern : serviceIntern.getAll()) {
                if (intern.getCinPassport().contains(searchText)) {
                    searchResults.add(intern);
                }
            }
            displayInterns2(searchResults);
        }


    }



public void main() {
}

}

