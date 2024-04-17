package tn.esprit.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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