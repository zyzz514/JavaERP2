package tn.esprit.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AfficherIntern {


    private ServiceIntern serviceIntern = new ServiceIntern();
    @FXML
    private Button ajouter,updateButton;

    @FXML
    private GridPane gridPane,GRID;

    @FXML
    private TextField PassUPD,SpecialityUPD,SectorUPD,ProUPD,imgUPD,StudyUPD;

    @FXML
    private TextField searchIN;

    private int chercherID,chercherUID;
    private String chercherLong,chercherLit;

    @FXML
    private HBox cardContainer;

    private void displayInterns2(List<Intern> interns) {
        cardContainer.getChildren().clear(); // Clear previous content
        for (Intern intern : interns) {
            // Create and configure your card here
            Pane card = createCard(intern);
            // Add the card to the container
            cardContainer.getChildren().add(card);
        }
    }

    private Pane createCard(Intern intern) {
        // Create a Pane or any suitable container for your card
        Pane card = new Pane();
        card.getStyleClass().add("card"); // Add CSS class for styling
        card.setPrefSize(200, 150); // Set the preferred size of the card

        // Create and configure the content of the card
        Label passportLabel = new Label("Passport: " + intern.getCinPassport());
        passportLabel.setLayoutX(10);
        passportLabel.setLayoutY(10);

        Label studyLevelLabel = new Label("Study Level: " + intern.getStudylevel());
        studyLevelLabel.setLayoutX(10);
        studyLevelLabel.setLayoutY(30);

        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(10);
        deleteButton.setLayoutY(70);
        deleteButton.setOnAction(event -> deleteIntern(intern));

        // Double-click event
        card.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Display intern data in text fields
                PassUPD.setText(intern.getCinPassport());
                StudyUPD.setText(intern.getStudylevel());
                // Add more fields as needed
            }
        });
        // Add content to the card
        card.getChildren().addAll(passportLabel, studyLevelLabel,deleteButton /* Add more labels or other nodes as needed */);

        // Add event handlers or other configurations as needed


        return card;
    }



   /* private void openEditDialog(Intern intern) {
        try {
            // Load the FXML file for the edit dialog
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditIntern.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            EditIntern controller = loader.getController();

            // Pass the selected intern to the controller
            controller.initData(intern);

            // Create a new stage for the edit dialog
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Edit Intern");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/




    @FXML
    void ajouterIntern() throws IOException {
        // Load SecondPage.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/AjouterIntern.fxml"));
        Stage stage = (Stage) ajouter.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void main() {
    }


    public void initialize() {
        displayInterns2(serviceIntern.getAll());
    }
    /*private void displayInterns(List<Intern> interns) {
        gridPane.getChildren().clear(); // Clear previous content
        int rowIndex = 0;
        for (Intern intern : interns) {
            // Populate the GridPane with intern data
            Label label1 = new Label(intern.getCinPassport());
            Label label2 = new Label(intern.getStudylevel());
            Label label3 = new Label(intern.getSpeciality());
            Label label4 = new Label(intern.getSector());
            Label label5 = new Label(intern.getProcontact());
            Label label6 = new Label(intern.getProfileimage());

            gridPane.add(label1, 0, rowIndex);
            gridPane.add(label2, 1, rowIndex);
            gridPane.add(label3, 2, rowIndex);
            gridPane.add(label4, 3, rowIndex);
            gridPane.add(label5, 4, rowIndex);
            gridPane.add(label6, 5, rowIndex);

            // Add delete button
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(event -> deleteIntern(intern));
            gridPane.add(deleteButton, 6, rowIndex);


            addDoubleClickHandler(label1, intern);
            addDoubleClickHandler(label2, intern);
            addDoubleClickHandler(label3, intern);
            addDoubleClickHandler(label4, intern);
            addDoubleClickHandler(label5, intern);
            addDoubleClickHandler(label6, intern);

            rowIndex++;


        }

    }

    private void addDoubleClickHandler(Label label, Intern intern) {
        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click detected
                // Display intern data in text fields
                PassUPD.setText(intern.getCinPassport());
                StudyUPD.setText(intern.getStudylevel());
                SpecialityUPD.setText(intern.getSpeciality());
                SectorUPD.setText(intern.getSector());
                ProUPD.setText(intern.getProcontact());
                imgUPD.setText(intern.getProfileimage());
                this.chercherID=intern.getId();
                this.chercherUID= intern.getUser_id();
                this.chercherLong=intern.getLongitude();
                this.chercherLit=intern.getLatitude();
            }
        });
        // Add action to update button
        updateButton.setOnAction(event -> updateIntern(this.chercherID,this.chercherUID,this.chercherLong,this.chercherLit));
    }*/

    @FXML
    private void updateIntern(int x,int y,String z,String w) {
        // Retrieve data from text fields
        String cinPassport = PassUPD.getText();
        String studyLevel = StudyUPD.getText();
        String speciality = SpecialityUPD.getText();
        String sector = SectorUPD.getText();
        String proContact = ProUPD.getText();
        String profileImage = imgUPD.getText();

        // Update the intern object with new data
        Intern updatedIntern = new Intern();
        updatedIntern.setCinPassport(cinPassport);
        updatedIntern.setStudylevel(studyLevel);
        updatedIntern.setSpeciality(speciality);
        updatedIntern.setSector(sector);
        updatedIntern.setProcontact(proContact);
        updatedIntern.setProfileimage(profileImage);
        updatedIntern.setLongitude(z);
        updatedIntern.setLatitude(w);
        updatedIntern.setUser_id(y);
        updatedIntern.setId(x);


        serviceIntern.update(updatedIntern);
        clearField();


    }
    @FXML
    private void clearField(){
        PassUPD.clear();
        StudyUPD.clear();
        SpecialityUPD.clear();
        SectorUPD.clear();
        ProUPD.clear();
        imgUPD.clear();
    }

    private void deleteIntern(Intern intern) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Item");
        alert.setContentText("Are you sure you want to delete this item?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                serviceIntern.delete(intern.getId());
                displayInterns2(serviceIntern.getAll());
            }
        });
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
            if (searchResults.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setHeaderText(null);
                alert.setContentText("No interns found matching the search criteria.");
                alert.showAndWait();
            } else {
                displayInterns2(searchResults);
            }
        }
    }


}

