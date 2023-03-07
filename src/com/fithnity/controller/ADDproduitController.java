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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
        int  poids =Integer.valueOf(poidsproduit.getText());
        String nom_produit =String.valueOf(nomproduit.getText());
        String description_produit =String.valueOf(descriptionproduit.getText());
   
  

      if(poidsproduit.getText().isEmpty() || nomproduit.getText().isEmpty()|| descriptionproduit.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
     else
        {
            produit p = new produit (nom_produit,poids,description_produit);
            produitService ps= new produitService() ;
             ps.addProduit(p);
            JOptionPane.showMessageDialog(null,"Succés de création ");
        
        }
    }

    @FXML
    private void afficheproduit(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDproduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
    }
    
}
