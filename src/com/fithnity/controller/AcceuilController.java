/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import com.fithnity.service.ServiceDemande;
/**
 * FXML Controller class
 *
 * @author andol
 */
public class AcceuilController implements Initializable {

    @FXML
    private Button fx_delete_demandePage;
    @FXML
    private Button fx_ajouter_demandePage;
    @FXML
    private Button fx_modif_demandePage;
    @FXML
    private Button fx_afficher_demande;

    /**
     * Initializes the controller class.
     */
/**
 * FXML Controller class
 *
 * @author andol
 */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         fx_ajouter_demandePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewDemande(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
               
           fx_delete_demandePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToSuppression(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }
        public void redirectToNewDemande(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/add_demande.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         public void redirectToSuppression(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DELETE_Dem.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
         }
     
  private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceDemande sd = new ServiceDemande();
        list = sd.getAllDemande();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
    
}
        
        
        
