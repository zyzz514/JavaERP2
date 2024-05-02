package tn.esprit.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.models.Intern;
import tn.esprit.services.ServiceIntern;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.Notifications;


public class AfficherIntern {


    private ServiceIntern serviceIntern = new ServiceIntern();
    @FXML
    private Button ajouter, updateButton;

    @FXML
    private GridPane gridPane, GRID;

    @FXML
    private TextField PassUPD, SpecialityUPD, SectorUPD, ProUPD, imgUPD, StudyUPD;

    @FXML
    private TextField searchIN;

    private int chercherID, chercherUID;
    private String chercherLong, chercherLit;

    @FXML
    private HBox cardContainer, cardContainerPIE, cardContainer01;

    @FXML
    private AnchorPane rootAnchor;

    @FXML
    private ScrollPane SS;


    private List<Intern> internList = new ArrayList<>();

    private void displayInterns2(List<Intern> interns) {
        int count = 0;
        float rt = 0.0f; // Initialize to float
        final float rt2;
        cardContainer.getChildren().clear(); // Clear previous content
        for (Intern intern : interns) {
            if ("informatique".equals(intern.getSpeciality())) { // Use equals() for string comparison
                count++;
            }
            // Create and configure your card here
            Pane card = createCard(intern);
            // Add the card to the container
            cardContainer.getChildren().add(card);
        }
        if (!interns.isEmpty()) { // Check if the interns list is not empty to avoid division by zero
            rt = (float) count / interns.size() * 100; // Calculate percentage
        }
        System.out.println("Percentage of interns with speciality 'informatique': " + rt + "%");

        // Create PieChart data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Informatique", count),
                new PieChart.Data("Other Specialities", interns.size() - count)
        );
        rt2 = rt;
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Specialities Distribution");


        cardContainer.getChildren().add(pieChart);
    }

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

        Button deleteButton = new Button("Delete");
        deleteButton.setLayoutX(80);
        deleteButton.setLayoutY(150);
        deleteButton.setOnAction(event -> deleteIntern(intern));

        // Double-click event
        card.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Display intern data in text fields
                PassUPD.setText(intern.getCinPassport());
                StudyUPD.setText(intern.getStudylevel());
                SpecialityUPD.setText(intern.getSpeciality());
                SectorUPD.setText(intern.getSector());
                ProUPD.setText(intern.getProcontact());
                imgUPD.setText(intern.getProfileimage());
                this.chercherID = intern.getId();
                this.chercherUID = intern.getUser_id();
                this.chercherLong = intern.getLongitude();
                this.chercherLit = intern.getLatitude();

            }
        });
        // Add content to the card
        card.getChildren().addAll(passportLabel, studyLevelLabel, specialityLabel, SectorLabel, ContactLabel, ImgLabel, deleteButton /* Add more labels or other nodes as needed */);

        // Add event handlers or other configurations as needed
        updateButton.setOnAction(event -> updateIntern(this.chercherID, this.chercherUID, this.chercherLong, this.chercherLit));

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


    public void initialize() {
        displayInterns2(serviceIntern.getAll());

    }


    @FXML
    private void updateIntern(int x, int y, String z, String w) {
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
    private void clearField() {
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
            displayInterns2(searchResults);
        }
    }



    public static void print(Node nodeToPrint) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(nodeToPrint.getScene().getWindow())) {
            boolean success = printerJob.printPage(nodeToPrint);
            if (success) {
                printerJob.endJob();
            } else {
                showPrintErrorAlert();
            }
        } else {
            showPrintErrorAlert();
        }
    }

    private static void showPrintErrorAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Print Error");
        alert.setHeaderText(null);
        alert.setContentText("Failed to print the document.");
        alert.showAndWait();
    }




    @FXML
    void handlePrintButtonAction2() {
        //AfficherIntern.handlePrintButtonAction2(cardContainerPIE);
      //  print(SS);
        List<Intern> interns = serviceIntern.getAll();
        generatePDF("src/main/resources/PDF/45.pdf",   interns );
    }

    @FXML
    public static void generatePDF(String filePath, List<Intern> interns) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();


            // Ajouter le titre
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 19, Font.BOLD);
            Paragraph title = new Paragraph("Liste des Réservations", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Ajouter la photo
          /*  Image image = Image.getInstance("/123.jpg"); // Remplacez "path/vers/votre/photo.jpg" par le chemin de votre photo
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleToFit(2, 2); // Ajustez la taille de l'image selon vos besoins
            document.add(image);*/

            // Ajouter une table avec les réservations
            PdfPTable table = new PdfPTable(6);
            // Ajouter les en-têtes de colonnes
            table.addCell("Passport");
            table.addCell("Study Level");
            table.addCell("Speciality");
            table.addCell("Sector");
            table.addCell("Pro Contact");
            table.addCell("Profile Image");


            // Ajouter les données de chaque réservation dans la table
            for (Intern intern : interns) {
                table.addCell(intern.getCinPassport());
                table.addCell(intern.getStudylevel());
                table.addCell(intern.getSpeciality());
                table.addCell(intern.getSector());
                table.addCell(intern.getProcontact());
                table.addCell(intern.getProfileimage());
            }

            // Ajouter la table au document
            document.add(table);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        System.out.println("ajout pdf succed");
    }


}











   /* public static void scaleForPrinting(Node node) {
        double scaleX = 2.0;
        double scaleY = 3.0;
        Scale scale = new Scale(scaleX, scaleY);
        node.getTransforms().add(scale);
    }*/



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



