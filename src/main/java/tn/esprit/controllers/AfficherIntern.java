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

    @FXML
    private TableView<Intern> tab;

    @FXML
    private TableColumn<Intern, Integer> IdColumn;

    @FXML
    private TableColumn<Intern, String> passportColumn;

    @FXML
    private TableColumn<Intern, String> studyColumn;

    @FXML
    private TableColumn<Intern, String> specialityColumn;

    @FXML
    private TableColumn<Intern, String> sectorColumn;

    @FXML
    private TableColumn<Intern, String> proColumn;

    @FXML
    private TableColumn<Intern, String> latitudeColumn;

    @FXML
    private TableColumn<Intern, String> longitudeColumn;

    @FXML
    private TableColumn<Intern, String> imageColumn;

    private ServiceIntern serviceIntern = new ServiceIntern();
    @FXML
    private Button ajouter;

    @FXML
    private TableColumn<Intern, Void> deleteColumn;

    @FXML
    private GridPane gridPane;



    private void deleteIntern(Intern intern) {


        // Display confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Deletion");
        alert.setContentText("Are you sure you want to delete this intern?");
        Optional<ButtonType> result = alert.showAndWait();

        // If user confirms deletion, proceed
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Delete intern from the database
            serviceIntern.delete(intern.getId());

            // Remove intern from TableView
            tab.getItems().remove(intern);
        }

    }
    //  @FXML
    public void initialize() {
        // Initialize TableView columns
        IdColumn.setCellValueFactory(cellData -> cellData.getValue().IdProperty().asObject());
        passportColumn.setCellValueFactory(cellData -> cellData.getValue().cinPassportProperty());
        studyColumn.setCellValueFactory(cellData -> cellData.getValue().studylevelProperty());
        specialityColumn.setCellValueFactory(cellData -> cellData.getValue().specialityProperty());
        sectorColumn.setCellValueFactory(cellData -> cellData.getValue().sectorProperty());
        proColumn.setCellValueFactory(cellData -> cellData.getValue().procontactProperty());
        latitudeColumn.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty());
        longitudeColumn.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty());
        imageColumn.setCellValueFactory(cellData -> cellData.getValue().profileimageProperty());

        List<Intern> internList = serviceIntern.getAll(); // Assuming getAll() retrieves all interns from the database
        tab.getItems().addAll(internList);

        deleteColumn.setCellFactory(param -> new TableCell<Intern, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Intern intern = getTableView().getItems().get(getIndex());
                    deleteIntern(intern);
                });
            }
                @Override
                protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
            });
        tab.setRowFactory(tv -> {
            TableRow<Intern> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) { // Double click
                    Intern selectedIntern = row.getItem();
                    // Handle double click action, e.g., open edit dialog
                    openEditDialog(selectedIntern);
                }
            });
            return row;
        });
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

   /* @FXML
    public void initialize() {
        List<Intern> internList = serviceIntern.getAll(); // Assuming getAll() retrieves all interns from the database
        populateGridPane(internList);
    }
    private void populateGridPane(List<Intern> internList) {
        int row = 0;
        for (Intern intern : internList) {
            gridPane.addRow(row,
                    new Label(Integer.toString(intern.getUserId())),
                    new Label(intern.getCinPassport()),
                    new Label(intern.getStudylevel()),
                    new Label(intern.getSpeciality()),
                    new Label(intern.getSector()),
                    new Label(intern.getProcontact()),
                    new Label(intern.getLatitude()),
                    new Label(intern.getLongitude()),
                    new Label(intern.getProfileimage()),
                    new Button("Delete")
            );

            // Set delete button action
            Button deleteButton = (Button) gridPane.getChildren().get(row * (gridPane.getColumnCount() - 1) + (gridPane.getColumnCount() - 1));
            deleteButton.setOnAction(event -> deleteIntern(intern));

            row++;
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

    @FXML
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
    }

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

