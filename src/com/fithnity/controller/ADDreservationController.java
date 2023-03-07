/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.utils.Mail;
import com.fithnity.service.livraisonService;
import com.fithnity.service.produitService;
import com.fithnity.service.reservationService;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ADDreservationController implements Initializable {

    @FXML
    private TextField catField;
    @FXML
    private TextField poidsField;
    @FXML
    private Button btn1;
    @FXML
    private DatePicker dateField;
    @FXML
    private Button btn2;
    @FXML
    private ComboBox<String> idva;
    @FXML
    private ComboBox<String> idvd;

    /**
     * Initializes the controller class.
     */
    reservationService sr = new reservationService() ;
    produitService ps = new produitService();
   reservation r = new reservation() ;
    @FXML
    private ComboBox<produit> produit_combo;
    int selectedModelId=0;

    public ADDreservationController() {
        this.sr = new reservationService();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    suitdash(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         ObservableList<String> list = FXCollections.observableArrayList("Tunis", "Sousse","Hammemet","Monastir" ) ;
         idva.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Tunis", "Sousse","Hammemet","Monastir") ;
        idvd.setItems(liste);
        // TODO
           LocalDate date = LocalDate.now(); // date par défaut : aujourd'hui
        dateField.setValue(date); 
        
              reservation r=new reservation();
        dateField.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate date = dateField.getValue();
                r.setDateReser(date);
            }
        });
                       dateField.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'affichage ou exécuter d'autres actions en réponse aux changements de date
        });
                       
                       
                       
         List<produit> produitList;
        try {
            produitList = ps.getAllProduit();
            for(produit p : produitList) {
    produit_combo.getItems().add(p);
    produit_combo.setConverter(new StringConverter<produit>() {
        @Override
        public String toString(produit p) {
            return p.getNom_produit();
        }
        @Override
        public produit fromString(String string) {
            return p;
        }
    });
}
 produit_combo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    if(newValue != null) {
        selectedModelId = newValue.getId_produit();
        // Do something with the selected model id
        System.out.println(selectedModelId);

    }
});  
        } catch (SQLException ex) {
            Logger.getLogger(ADDreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

            
                       
    }    

    
           



    @FXML
    private void ajoutLivraison(ActionEvent event) {
     
    int  poids =Integer.valueOf(poidsField.getText());
    int prix =Integer.valueOf(catField.getText());
    String vd= idvd.getSelectionModel().getSelectedItem();
    String va= idva.getSelectionModel().getSelectedItem();
    LocalDate date = dateField.getValue();
         
//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//String formattedDate = date.format(formatter); 
 
      if(poidsField.getText().isEmpty() || catField.getText().isEmpty())
     {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("Veuillez remplir tous les champs");
             alert.showAndWait();
     }
     else
        {
            reservation U1 = new reservation (poids,prix,vd,va,date,selectedModelId);
            reservationService uc= new reservationService() ;
             System.out.println("Connexion réussie!");
                 String recipient = "yassine.benbelgacem@esprit.tn";
                 try {
                 com.fithnity.utils.Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();}
             uc.addReservation(U1);
            
            JOptionPane.showMessageDialog(null,"Succés De Création ");
        
        }
   
    }

    @FXML
    private void suitdash(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDreservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }


    @FXML
    private void SelectId(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
             Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARDreservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

  
    
}
