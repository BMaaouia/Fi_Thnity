/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.produitService;
import com.fithnity.service.reservationService;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import static java.awt.SystemColor.text;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */


public class ADDproduitController implements Initializable {

    
    @FXML
    private TextField nomproduit;
    @FXML
    private TextField poidsproduit;
    @FXML
    private TextField descriptionproduit;
    
    produitService sp = new produitService() ;
   produit p = new produit() ;
    @FXML
    private Button btnajoutProduit;
    @FXML
    private Button btnafficheproduit;
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_blog;

    /**
     * Initializes the controller class.
     */
   
   
    public ADDproduitController() {
        this.sp = new produitService();
           
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btnajoutProduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajoutproduit(event);
            }
        });
           
        btnafficheproduit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    afficheproduit(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        
        // TODO
    }

    private void addProduit(ActionEvent event) {
        
        
      
   
    }


//    private void VersHome(ActionEvent event) throws IOException {
//         Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDproduit.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//        
//    }

    @FXML
    private void ajoutproduit(ActionEvent event) {
    String nom_produit = nomproduit.getText().trim();
    String description_produit = descriptionproduit.getText().trim();

    // Vérifier que les champs ne sont pas vides
    if (poidsproduit.getText().isEmpty() || nom_produit.isEmpty() || description_produit.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }

    // Vérifier que le poids est un nombre positif
    int poids = 0;
    try {
        poids = (int) Double.parseDouble(poidsproduit.getText());
        if (poids <= 0) {
            throw new NumberFormatException();
        }
    } catch (NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Le poids doit être un nombre positif");
        alert.showAndWait();
        return;
    }

    // Vérifier que le nom du produit et la description sont des chaînes de caractères
    if (!nom_produit.isEmpty() || !description_produit.isEmpty())
    if (!nom_produit.matches("[a-zA-Z]+") || !description_produit.matches("[a-zA-Z]+")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Le nom du produit et la description doivent être des chaînes de caractères");
        alert.showAndWait();
        return;
    }

    // Si la validation de saisie est OK, créer le produit
    produit p = new produit(nom_produit, poids, description_produit);
    produitService ps = new produitService();
    ps.addProduit(p);
    JOptionPane.showMessageDialog(null, "Succés de création");
}

    @FXML
    private void afficheproduit(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDproduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }

    @FXML
    private void go_user(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Profile.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reclamation(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajouterreclamationfront.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blog(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajout_c.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
