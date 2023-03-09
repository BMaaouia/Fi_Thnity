/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.fithnity.entity.Offre;
import com.fithnity.service.ServiceOffre;
import static com.sun.glass.ui.Cursor.setVisible;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class ADD_offreController implements Initializable {

    @FXML
    private TextField fx_nbreposte;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private Button fx_button_ajout_offre;
    @FXML
    private TextField fx_salaire;
    @FXML
    private ComboBox<String> id_metier;
    @FXML
    private ComboBox<String> id_ville;
    @FXML
    private DatePicker Offre_date;
    @FXML
    private TextField fx_secteur;
    @FXML
    private Label label_secteurverif;
    @FXML
    private Label label_nbrposteverif;
    @FXML
    private Label label_salaireverif;
    @FXML
    private Label lb_err;
    @FXML
    private Label lb_success;
    @FXML
    private Label label_metier_err;
    @FXML
    private Label label_ville_err;
    @FXML
    private Label secteur_alpha;
    @FXML
    private Label nbreposte_numerique;
    @FXML
    private Label salaire_num;
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
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules", "Agent de service client");
    id_metier.setItems(list);
    
    ObservableList<String> liste = FXCollections.observableArrayList("Tunis","Bizerte", "Gabès", "Gafsa", "Hammam-Lif", "Jendouba","Kairouan","Kasserine","Sousse");
    id_ville.setItems(liste);
    // initialisation du DatePicker
    LocalDate date = LocalDate.now(); // date par défaut : aujourd'hui
    Offre_date.setValue(date); // affectation de la date au DatePicker
        
  
    
        fx_button_ajout_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_offre();
            }
        });
          fx_afiche_offrePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToAcceuil(event);
                } catch (Exception e) {
                    System.out.println(e);
            }
            }
        });
          Offre o=new Offre();
        Offre_date.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate dateOffre = Offre_date.getValue();
               // o.setdateOffre(dateOffre);
            }
        });
                       Offre_date.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'affichage ou exécuter d'autres actions en réponse aux changements de date
        });
    }   
        
        
        
        

    @FXML
    private void ajouter_offre() {
        // Check if job and city are selected
String selectedJob = id_metier.getValue();
if (selectedJob == null || selectedJob.trim().isEmpty()) {
    label_metier_err.setVisible(true);
} else {
    label_metier_err.setVisible(false);
}

String selectedCity = id_ville.getValue();
if (selectedCity == null || selectedCity.trim().isEmpty()) {
    label_ville_err.setVisible(true);
} else {
    label_ville_err.setVisible(false);
}

// Check if all required fields are filled
if (fx_nbreposte.getText().toString().equals("") || fx_salaire.getText().toString().equals("")
        || fx_secteur.getText().toString().equals("")) {
    lb_success.setVisible(false);

    if (fx_secteur.getText().toString().equals("")) {
        label_secteurverif.setVisible(true);
    } else {
        label_secteurverif.setVisible(false);
    }
    if (fx_nbreposte.getText().toString().equals("")) {
        label_nbrposteverif.setVisible(true);
    } else {
        label_nbrposteverif.setVisible(false);
    }
    if (fx_salaire.getText().toString().equals("")) {
        label_salaireverif.setVisible(true);
    } else {
        label_salaireverif.setVisible(false);
    }
} else {
    // All required fields are filled
    lb_err.setVisible(false);
    label_secteurverif.setVisible(false);
    label_nbrposteverif.setVisible(false);
    label_salaireverif.setVisible(false);
   // lb_success.setVisible(true);

    // Validate input using regular expressions
    Pattern alphaPattern = Pattern.compile("[a-zA-Z]+");
    Pattern numPattern = Pattern.compile("[0-9]+");
    Matcher secteurMatcher = alphaPattern.matcher(fx_secteur.getText());
    Matcher nbreposteMatcher = numPattern.matcher(fx_nbreposte.getText());
    Matcher salaireMatcher = numPattern.matcher(fx_salaire.getText());

    if (!secteurMatcher.matches()) {
        secteur_alpha.setVisible(true);
    } else {
        secteur_alpha.setVisible(false);
    }

    if (!nbreposteMatcher.matches()) {
        nbreposte_numerique.setVisible(true);
    } else {
        nbreposte_numerique.setVisible(false);
    }

    if (!salaireMatcher.matches()) {
        salaire_num.setVisible(true);
    } else {
        salaire_num.setVisible(false);
   
        
        
        
        
                 ServiceOffre o_Service = new ServiceOffre();
                 Offre o = new Offre();
                    
         
 
                 if(fx_nbreposte.getText().isEmpty()|| fx_salaire.getText().isEmpty()){
                     Alert alert = new Alert(Alert.AlertType.ERROR);
                     alert.setTitle("Erreur");
                     alert.setHeaderText("ajouter des champs");
                     
                     alert.showAndWait();
                     
                     
                     
                     
                     
                     
                     
                     
                 }else{
                     
                     
                     o.setNombredeposte(String.valueOf(fx_nbreposte.getText()));
                     o.setSalaire(String.valueOf(fx_salaire.getText()));
                     o.setSecteur(String.valueOf(fx_secteur.getText()));
                     String M= id_metier.getSelectionModel().getSelectedItem().toString();
                     String V= id_ville.getSelectionModel().getSelectedItem().toString();
                     o.setMetier(M);
                     o.setVille(V);
                     
                     java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
                     o.setDateOffre(currentDate);
                     
                     
                     
                     o_Service.ajouter(o);
                     
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                     alert.setTitle("Information Dialog");
                     alert.setHeaderText(null);
                     alert.setContentText("Your OFFRE has been ADDED successfully!");
                     alert.show();
                     
                     
                     
                 }
        }
    }
    }
        

 public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

  
         
       
    
}

    

