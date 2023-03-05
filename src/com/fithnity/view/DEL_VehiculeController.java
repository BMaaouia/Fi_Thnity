/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.view;

import com.fithnity.entities.Vehicule;
import com.fithnity.services.ServiceVehicule;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */



public class DEL_VehiculeController implements Initializable {

    @FXML
    private TextField fx_sup_v;
    @FXML
    private Button btn_sup_v;
    @FXML
    private Button btn_back;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
            btn_sup_v.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DEL_v();
            }
        });
            btn_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
              redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }    

    @FXML
    private void DEL_v() {
          //produit C = tvCommentaire.getSelectionModel().getSelectedItem();
        Vehicule p=new Vehicule();
           if(fx_sup_v.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        ServiceVehicule ps = new ServiceVehicule();
       // p.setId_produit(Integer.valueOf(id_produit_sup.getText()));
        ps.deleteVehicule(Integer.valueOf(fx_sup_v.getText()));
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Vehicule has been DELETED successfully!");
        alert.show();
    }}
    
      public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com//fithnity/view/DASHBOARD_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
    
}
