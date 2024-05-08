package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import tn.esprit.models.Internshiprequest;
import tn.esprit.services.ServiceInternShipRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AfficherRequest {

    @FXML
    private Button ajouter,updateButton;

    @FXML
    private TextField searchIN;

    @FXML
    private TextField CinUPD, CvUPD,RecUPD;

    @FXML
    private HBox cardContainer;

    private int chercherID, chercherUID2,chercherUID;

    private ServiceInternShipRequest serv = new ServiceInternShipRequest() ;
    @FXML
    void ajouterReq() throws IOException {
        // Load SecondPage.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/AjouterInternSHIP.fxml"));
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void initialize() {
        displayReq2(serv.getAll());

    }

    @FXML
    void searchReq() {
        String searchText = searchIN.getText().trim();
        if (searchText.isEmpty()) {
            displayReq2(serv.getAll()); // Show all interns if search text is empty
        } else {
            List<Internshiprequest> searchResults = new ArrayList<>();
            for (Internshiprequest Requ : serv.getAll()) {
                if (Requ.getCinfile().contains(searchText)) {
                    searchResults.add(Requ);
                }
            }
            displayReq2(searchResults);
        }
    }

    private void displayReq2(List<Internshiprequest> internsRequest) {

        cardContainer.getChildren().clear(); // Clear previous content
        for (Internshiprequest req : internsRequest) {

            // Create and configure your card here
            Pane card = createCard(req);
            // Add the card to the container
            cardContainer.getChildren().add(card);
        }
    }

    private Pane createCard(Internshiprequest request) {
        // Create a Pane or any suitable container for your card
        Pane card = new Pane();
        card.getStyleClass().add("card"); // Add CSS class for styling
        card.setPrefSize(220, 200); // Set the preferred size of the card

        // Create and configure the content of the card
        Label passportLabel = new Label("Cin file: " + request.getCinfile());
        passportLabel.setLayoutX(10);
        passportLabel.setLayoutY(10);

        Label studyLevelLabel = new Label("Cv file: " + request.getCvfile());
        studyLevelLabel.setLayoutX(10);
        studyLevelLabel.setLayoutY(30);

        Label specialityLabel = new Label("Speciality: " +request.getReclettrefile());
        specialityLabel.setLayoutX(10);
        specialityLabel.setLayoutY(50);



        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(80);
        deleteButton.setLayoutY(150);
        deleteButton.setOnAction(event -> deleteIntenReq(request));

        // Double-click event
        card.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Display intern data in text fields
                CinUPD.setText(request.getCinfile());
                CvUPD.setText(request.getCvfile());
                RecUPD.setText(request.getReclettrefile());

                this.chercherID = request.getId();
                this.chercherUID = request.getInternship_id();
                this.chercherUID2 = request.getIntern_id();


            }
        });
        // Add content to the card
        card.getChildren().addAll(passportLabel, studyLevelLabel, specialityLabel, deleteButton /* Add more labels or other nodes as needed */);

        // Add event handlers or other configurations as needed
        updateButton.setOnAction(event -> updateIntern(this.chercherID, this.chercherUID2, this.chercherUID));

        return card;
    }
    private void deleteIntenReq(Internshiprequest request) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Item");
        alert.setContentText("Are you sure you want to delete this item?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                serv.delete(request.getId());
                displayReq2(serv.getAll());
            }
        });
    }

    @FXML
    private void updateIntern(int x, int y, int z) {
        // Retrieve data from text fields
        String cinFile = CinUPD.getText();
        String cvFile = CinUPD.getText();
        String Rec = RecUPD.getText();


        // Update the intern object with new data
        Internshiprequest updatedReq = new Internshiprequest();
        updatedReq.setCinfile(cinFile);
        updatedReq.setCvfile(cvFile);
        updatedReq.setReclettrefile(Rec);
        updatedReq.setIntern_id(y);
        updatedReq.setInternship_id(z);
        updatedReq.setId(x);


        serv.update(updatedReq);
        clearField();


    }
    @FXML
    private void clearField() {
        CinUPD.clear();
        CvUPD.clear();
        RecUPD.clear();

    }


}
