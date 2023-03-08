/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.livraisonService;
import com.fithnity.service.reservationService;
import com.fithnity.entity.livraison;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ADDlivraisonController implements Initializable {

   
 //   @FXML
   // private TextField fx_Description;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button fx_back;
    @FXML
    private CheckBox fx_etatAnnul;
    @FXML
    private CheckBox fx_etatArriv;
    
    @FXML
    private CheckBox fx_etatEncours;
    @FXML
    private Label etatlabel;
    
  //  @FXML
  //  private Label label_description;
    @FXML
    private Label immatriculationlabel;
    @FXML
    private TextField label_description;
    @FXML
    private ComboBox<reservation> reservation_combo;
    @FXML
    private Label etatlabel1;
    reservationService rs = new reservationService();
    int selectedModelId=0;
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_l();
            }
        });
           
        fx_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        
        
        List<reservation> reservationList;
        try {
            reservationList = rs.getAllReservation();
            for(reservation r : reservationList) {
    reservation_combo.getItems().add(r);
    reservation_combo.setConverter(new StringConverter<reservation>() {
        @Override
        public String toString(reservation r) {
            return Integer.toString(r.getPoids());
        }
        @Override
        public reservation fromString(String string) {
            return r;
        }
    });
}
 reservation_combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if(newValue != null) {
        selectedModelId = newValue.getId_r();
        // Do something with the selected model id
        System.out.println(selectedModelId);

    }
});  
        } catch (SQLException ex) {
            Logger.getLogger(ADDreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }  

    @FXML
    private void ajouter_l() {
        {
        
        boolean valid = true;
        
      
        livraisonService V_Service = new livraisonService();
        
        String description =String.valueOf(label_description.getText());
        if(label_description.getText().isEmpty())
        
        {
            valid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } else {
            label_description.setVisible(false);
            
            
          int etat = 0;

          if (fx_etatArriv.isSelected()) {
              etat = 1;
               
          } else if (fx_etatEncours.isSelected()) {
              etat = 0;
       
          } else if (fx_etatAnnul.isSelected()) {
              etat = 0;
               
          }
          livraison v = new livraison(etat,label_description.getText(),selectedModelId);
//          v.setDescription(label_description.getText());
//          v.setEtat(etat);
//          v.setId_r(selectedModelId);
            
          if(fx_etatAnnul.isSelected()) {
    fx_etatEncours.setText("EN Cours");
} else {
    fx_etatArriv.setText("Annuler");
}
            
            try {
                 
                V_Service.addlivraison(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Le véhicule a été ajouté avec succès !");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de l'ajout du livraision : " + e.getMessage());
                alert.show();
            }
        }
    }

    }
    
    public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBORDlivraison.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
    
}
