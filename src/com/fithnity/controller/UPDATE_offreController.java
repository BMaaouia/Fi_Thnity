/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.fithnity.entity.Offre;
import com.fithnity.service.ServiceOffre;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class UPDATE_offreController implements Initializable {

    private TextField id_metier_up;
    private TextField id_sec_up;
    private TextField id_ville_up;
    @FXML
    private Button btn_modif_offre;
    private Button fx_retour_home;
    private TextField id_off_up;
    private TextField id_nbrposte_up;
    private TextField id_voiture_up;
    private TextField id_duree_up;
    private TextField id_salaire_up;
    @FXML
    private TextField fx_nbreposte;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private TextField fx_salaire;
    @FXML
    private ComboBox<String> id_metier;
    @FXML
    private ComboBox<String> id_ville;
    @FXML
    private TextField fx_secteur;
    private TableView<Offre> id_liste;
    private TableColumn<Offre, String> id_metier2;
    private TableColumn<Offre, String> id_secteur;
    private TableColumn<Offre, String> id_v;
    private TableColumn<Offre, String> id_nbre_poste;
    private TableColumn<Offre, String> id_salaire;
    private TableColumn<Offre, Date> id_date;
    @FXML
    private Button id_liste_offre;
    @FXML
    private ListView<Offre> id_liste2;
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
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;
    

    /**
     * Initializes the controller class.
     */
  
 @Override
    public void initialize(URL url, ResourceBundle rb) {
         //**************************
        ServiceOffre so = new ServiceOffre();
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) so.getAllOffre();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste2.setItems(observableList);
        //*****************
        ObservableList<String> list = FXCollections.observableArrayList("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules", "Agent de service client");
    id_metier.setItems(list);
    
    ObservableList<String> liste = FXCollections.observableArrayList("Tunis","Bizerte", "Gabès", "Gafsa", "Hammam-Lif", "Jendouba","Kairouan","Kasserine","Sousse");
    id_ville.setItems(liste);
    
  id_liste2.setOnMouseClicked(event->{
            

 java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
        Offre current = id_liste2.getSelectionModel().getSelectedItem();
        // txt_id.setText(Integer.toString(current.getId()));
       
         current.getoffre_id();
//        txt_nom.setText(current.getNom());
    //    txt_prenom.setText(current.getPrenom());
       fx_nbreposte.setText(current.getNombredeposte());
       fx_salaire.setText(current.getNombredeposte());
       fx_secteur.setText(current.getSecteur());
         //id_metier.setText   
        btn_modif_offre.setOnAction(e->{
            
             ServiceOffre o_Service = new ServiceOffre();
        if(fx_nbreposte.getText().isEmpty()|| fx_salaire.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
       
        
        current.setNombredeposte(String.valueOf(fx_nbreposte.getText()));
        current.setSalaire(String.valueOf(fx_salaire.getText()));
        current.setSecteur(String.valueOf(fx_secteur.getText()));
      //  o.setMetier(String.valueOf(id_metier.getText()));
            String M= id_metier.getSelectionModel().getSelectedItem().toString(); 
            String V= id_ville.getSelectionModel().getSelectedItem().toString();   
            current.setMetier(M);
            current.setVille(V);
            
            o_Service.update(current);
            
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your OFFRE has been Modified successfully!");
              alert.show();
   

        }
        
        
        });

    });

            

    }    
    
    

    @FXML
    private void listeoffre() {
        ServiceOffre so = new ServiceOffre();
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) so.getAllOffre();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_metier2.setCellValueFactory(new PropertyValueFactory<>("metier"));
        id_secteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));
        id_v.setCellValueFactory(new PropertyValueFactory<>("ville"));
        id_nbre_poste.setCellValueFactory(new PropertyValueFactory<>("Nombredeposte"));
        id_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("dateOffre"));

        
    }
    


    @FXML
    private void retour(ActionEvent event) throws IOException {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view_offre.fxml"));
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
    private void go_reservationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDlivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
}

    
    
    
    
    
   