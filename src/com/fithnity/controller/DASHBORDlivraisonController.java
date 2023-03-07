/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.service.livraisonService;
import com.fithnity.service.produitService;
import com.fithnity.entity.livraison;
import com.fithnity.entity.produit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DASHBORDlivraisonController implements Initializable {

    @FXML
    private Button supp;
    @FXML
    private TextField rech;
    @FXML
    private TextField description_field;
    @FXML
    private Button supp1;
    @FXML
    private CheckBox fx_etatArriv;
    @FXML
    private ListView<livraison> id_list2;
    @FXML
    private CheckBox fx_etatEncours;
    @FXML
    private CheckBox fx_etatAnnul;
    @FXML
    private Button back_livraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         back_livraison.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    back(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         livraisonService uc =new livraisonService();
              ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.getAlllivraison();
        }  catch (Exception ex) {
            Logger.getLogger(DASHBORDlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
         id_list2.setItems(observableList);
        id_list2.setOnMouseClicked(event->{
            


        livraison current = id_list2.getSelectionModel().getSelectedItem();
       
       
         //current.getId_produit();
       
       description_field.setText(current.getDescription());
                
                 supp1.setOnAction(e->{
            
             livraisonService o_Service = new livraisonService();
                     
                          current.setDescription(description_field.getText());
                          try {
                o_Service.update(current);
            } catch (SQLException ex) {
                Logger.getLogger(DASHBORDlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your reservation has been Modified successfully!");
              alert.show();
   
        Parent root2 = null;
            try {
                root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBORDlivraison.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DASHBORDlivraisonController.class.getName()).log(Level.SEVERE, null, ex);
            }
    Stage window = (Stage) supp1.getScene().getWindow();
    window.setScene(new Scene(root2));
                         
                         
                });
        });             
                         
        // TODO
    }    

    @FXML
    private void Supprimer(ActionEvent event) throws Exception{
             livraisonService uc =new  livraisonService();
     livraison p = id_list2.getSelectionModel().getSelectedItem();
  
    id_list2.getItems().remove(p);
      uc.delete(p);
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit suprimée avec succés!");
        alert.show();
    }

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDlivraison.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
