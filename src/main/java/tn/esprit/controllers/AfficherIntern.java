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
    private TableColumn<Intern, Integer> userIdColumn;

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
    @FXML
    public void initialize() {
        // Initialize TableView columns
        userIdColumn.setCellValueFactory(cellData -> cellData.getValue().userIdProperty().asObject());
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


}