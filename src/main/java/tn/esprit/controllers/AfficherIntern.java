package tn.esprit.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfficherIntern {

    private ServiceIntern serviceIntern = new ServiceIntern();
    @FXML
    private Button ajouter;


    @FXML
    private GridPane gridPane;


    //  @FXML
    public void initialize() {

        List<Intern> internList = serviceIntern.getAll(); // Assuming getAll() retrieves all interns from the database


            // Iterate over the list and populate the GridPane
            int rowIndex = 0;
            for (Intern intern : internList) {
                Label label1 = new Label(intern.getCinPassport());
                Label label2 = new Label(intern.getStudylevel());
                Label label3 = new Label(intern.getSpeciality());
                Label label4 = new Label(intern.getSector());
                Label label5 = new Label(intern.getProcontact());
                Label label6 = new Label(intern.getProfileimage());

                // Add labels to the GridPane
                gridPane.add(label1, 0, rowIndex);
                gridPane.add(label2, 1, rowIndex);
                gridPane.add(label3, 2, rowIndex);
                gridPane.add(label4, 3, rowIndex);
                gridPane.add(label5, 4, rowIndex);
                gridPane.add(label6, 5, rowIndex);

                // Create and add a delete button for each row
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(event -> {
                    // Display confirmation dialog
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setHeaderText("Delete Item");
                    alert.setContentText("Are you sure you want to delete this item?");

                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.OK) {
                            // Handle delete action
                            serviceIntern.delete(intern.getId()); // Assuming delete method takes the id as parameter
                            gridPane.getChildren().removeAll(label1, label2, label3, label4, label5, label6, deleteButton);
                        }
                    });
                });
                gridPane.add(deleteButton, 6, rowIndex);

                rowIndex++; // Move to the next row for the next intern's data
            }

       // t
    }

    private void openEditDialog(Intern intern) {
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
    }



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

   /* @FXML
    void updateIntern() {
        // Get the selected intern from the table
        Intern selectedIntern = tab.getSelectionModel().getSelectedItem();
        if (selectedIntern != null) {
            // Open the edit dialog
            openEditInternDialog(selectedIntern);
        } else {
            // Show an error message if no intern is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select an intern to update.");
            alert.showAndWait();
        }
    }*/

    private void openEditInternDialog(Intern intern) {
        // Load the FXML file for the edit dialog
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditInternDialog.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Get the controller for the edit dialog
        EditIntern controller = loader.getController();

        // Pass the selected intern to the controller
        controller.setIntern(intern);

        // Show the edit dialog in a new window
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

        // Update TableView if changes were made
        // You can implement this part based on your specific requirement
    }


}

