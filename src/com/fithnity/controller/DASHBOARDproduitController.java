/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.utils.Pdf;
import com.fithnity.utils.Pdf2;
import com.fithnity.service.produitService;
import com.fithnity.service.reservationService;
import com.fithnity.entity.produit;
import com.fithnity.entity.reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class DASHBOARDproduitController implements Initializable {

    @FXML
    private TextField nom_filled;
    @FXML
    private TextField poids_field;
    @FXML
    private TextField rech;
    private TableView<produit> id_list;
    private TableColumn<produit, String> id_nom;
    private TableColumn<produit, Integer> id_poids;
    private TableColumn<produit, String> fx_description;
    @FXML
    private TextField description_field;
    @FXML
    private Button id_liste;
    @FXML
    private ListView<produit> id_list2;
    @FXML
    private Button supp1;
    @FXML
    private Button supp;
    @FXML
    private Button back_produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         back_produit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    back(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        
        // TODO
        produitService uc =new produitService();
              ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.getAllProduit();
        } catch (SQLException ex) {
            Logger.getLogger(DASHBOARDproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
         id_list2.setItems(observableList);
        id_list2.setOnMouseClicked(event->{
            


        produit current = id_list2.getSelectionModel().getSelectedItem();
       
       
         current.getId_produit();
       nom_filled.setText(current.getNom_produit());
       poids_field.setText(Integer.toString(current.getPoids()));
       description_field.setText(current.getDescription());
                System.out.println(current);
                 supp1.setOnAction(e->{
            System.out.println(current);
             produitService o_Service = new produitService();
                         
                         
                         
                         current.setNom_produit(nom_filled.getText());
        current.setPoids(Integer.parseInt(poids_field.getText()));
                          current.setDescription(description_field.getText());
                          try {
                o_Service.update(current);
            } catch (SQLException ex) {
                Logger.getLogger(DASHBOARDproduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your reservation has been Modified successfully!");
              alert.show();
   
        Parent root2 = null;
            try {
                root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBOARDproduit.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DASHBOARDproduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
    Stage window = (Stage) supp1.getScene().getWindow();
    window.setScene(new Scene(root2));
                         
                         
                });
        });             
                         
                         
                     
     

    }
    private void handleTableSelection() {
    produit offreSelectionnee = id_list2.getSelectionModel().getSelectedItem();
    if (offreSelectionnee != null) {
        // Récupérer les données de la ligne sélectionnée
        
      
        String nom = offreSelectionnee.getNom_produit();
        int poids= offreSelectionnee.getPoids();
        String description = offreSelectionnee.getDescription();

        // Faire quelque chose avec ces données (par exemple les afficher dans une boîte de dialogue)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information sur l'offre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText(  "\nnom : " + nom + "\npoids : " + poids + "\ndescription : " + description 
               );
        alert.showAndWait();
       }
    
    id_list2.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) { // une seule pression de souris
        handleTableSelection();
    }
});

id_list2.setOnKeyPressed(event -> {
    if (event.getCode().equals(KeyCode.ENTER)) { // appui sur la touche Entrée
        handleTableSelection();
    }
});

}


    

   
    private void update(ActionEvent event) {
             int id = Integer.valueOf(id_list.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
        produitService gallerieService = new produitService();
        produit currentGallerie = gallerieService.getUniqueProduit(id);

        id_poids.setText(String.valueOf(currentGallerie.getPoids()));
        id_nom.setText(currentGallerie.getNom_produit());
        fx_description.setText(currentGallerie.getDescription());

    }

 @FXML
    private void Supprimer(ActionEvent event) throws SQLException {

        produitService uc =new produitService();
     produit p = id_list2.getSelectionModel().getSelectedItem();
  
    id_list2.getItems().remove(p);
      uc.delete(p);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit suprimée avec succés!");
        alert.show();
       
    }

    @FXML
    private void lister() {
      produitService uc =new produitService();
        ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.getAllProduit();
        } catch (SQLException ex) {
            Logger.getLogger(DASHBOARDproduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
         id_list.setItems(observableList);
        id_nom.setCellValueFactory(new PropertyValueFactory<>("nom_produit"));
         id_poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
        fx_description.setCellValueFactory(new PropertyValueFactory<>("description"));
    
    }

    @FXML
    private void pdf(ActionEvent event) {        
        Pdf2 pd=new Pdf2();
        try{
        pd.GeneratePdf("Liste des produits");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(produitService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    

//    @FXML
//    private void FiltrerKeyReleased3()
//{

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {String searchTerm = rech.getText();
    produitService su = new produitService();
    ObservableList<produit> list = su.searchent3(searchTerm);
    List<produit> filteredList = list.stream()
        .filter(entretient -> entretient.getNom_produit().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());
   id_list2.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDproduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
  

    

    
}
