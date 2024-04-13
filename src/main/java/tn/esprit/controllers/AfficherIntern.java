
package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AfficherIntern implements Initializable {

   @FXML
   private Label lbIntern;

   public void setLbIntern(String lbIntern) {
      this.lbIntern.setText(lbIntern);
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {

   }
}

