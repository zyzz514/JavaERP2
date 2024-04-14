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


public class AjouterIntern  {

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
    ServiceIntern i = new ServiceIntern();
    @FXML
    void afficherIntern(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherIntern.fxml"));

        try{
            Parent root = loader.load();
            AfficherIntern ai = loader.getController();

            ai.setLbIntern(i.getAll().toString());
            tab.getScene().setRoot(root);

        }catch (IOException e){
            System.err.println(e.getMessage());
        }

    }

    @FXML
    void ajouterIntern(ActionEvent event) {

        Intern p = new Intern();

        p.setUser_id(Integer.parseInt(user_id.getText()));
        p.setCin_passport(cinP.getText());
        p.setStudylevel(studyLeV.getText());
        p.setSpeciality(special.getText());
        p.setSector(sector.getText());
        p.setProcontact(procontact.getText());
        p.setLatitude(latitude.getText());
        p.setLongitude(longitude.getText());
        p.setProfileimage(profileimage.getText());

        i.add(p);

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


}
