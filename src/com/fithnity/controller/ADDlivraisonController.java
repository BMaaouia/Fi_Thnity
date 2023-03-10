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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javafx.beans.binding.Bindings.isEmpty;
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
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_offre;

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
             return r.getReference();
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
        
        String regex = "^[a-zA-Z ]+$"; //expression régulière pour accepter uniquement des lettres et espaces
        Pattern pattern = Pattern.compile(regex);
        livraisonService V_Service = new livraisonService();
        
        String description =String.valueOf(label_description.getText());
        if (label_description.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez remplir le champ description.");
        alert.showAndWait();
    } else if (!pattern.matcher(label_description.getText()).matches()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Le champ description doit contenir uniquement des lettres et des espaces.");
        alert.showAndWait();
    
        } else {
            label_description.setVisible(false);
            
            
          int etat = 0;
         

// Check that only one checkbox is selected
if (fx_etatArriv.isSelected() && (fx_etatAnnul.isSelected() || fx_etatEncours.isSelected())) {
  {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez remplir le champ Etat.");
        alert.showAndWait();
    } 
} else if (fx_etatArriv.isSelected() && (fx_etatAnnul.isSelected() || fx_etatEncours.isSelected())) {
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez remplir le champ Etat.");
        alert.showAndWait();
    } 
} else if (fx_etatArriv.isSelected() && (fx_etatAnnul.isSelected() || fx_etatEncours.isSelected())) {
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Veuillez remplir le champ Etat.");
        alert.showAndWait();
    } 
}
          

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

    @FXML
    private void go_userback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reclamationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blogback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_employeback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_employée.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_offreback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/commands.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
