/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.produitService;
import Services.reservationService;
import entity.produit;
import entity.reservation;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */


public class ADDproduitController implements Initializable {

    
    @FXML
    private Button toHome;
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
        // TODO
    }

    private void addProduit(ActionEvent event) {
        
      
   
    }


    @FXML
    private void VersHome(ActionEvent event) {
    }

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
    private void afficheproduit(ActionEvent event) {
    }
    
}
