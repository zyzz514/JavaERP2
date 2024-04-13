package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AjouterIntern  {

    @FXML
    private TableView tab;

    @FXML
    private TextField cinP;

    @FXML
    private TextField studyLeV;

    @FXML
    private TextField special;
    ServiceIntern intern = new ServiceIntern();
    @FXML
    void afficherIntern(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherIntern.fxml"));

        try{
            Parent root = loader.load();
            AfficherIntern ai = loader.getController();

            ai.setLbIntern(intern.getAll().toString());
            tab.getScene().setRoot(root);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

}
