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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UPDATE_VehiculeController implements Initializable {

    @FXML
    private TextField fx_model_up;
    @FXML
    private TextField fx_mat_up;
    @FXML
    private TextField fx_cat_up;
    private TextField fx_etat_up;
    @FXML
    private Button btn_update;
    @FXML
    private Button fx_back;
    @FXML
    private TextField fx_id_up;
    @FXML
    private CheckBox fx_update1;
    @FXML
    private CheckBox fx_update2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               update_v();
            }
        });
        // TODO
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
    }    

    @FXML
    private void update_v() {
          ServiceVehicule V_Service = new ServiceVehicule();
        Vehicule v = new Vehicule();
        if(fx_model_up.getText().isEmpty() || fx_mat_up.getText().isEmpty() || fx_cat_up.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } else {
            v.setModele(fx_model_up.getText());
            v.setImmatriculation(fx_mat_up.getText());
            v.setCategorie(fx_cat_up.getText());
            v.setEtat(fx_update1.isSelected());
            v.setEtat(fx_update2.isSelected());
        V_Service.updateVehicule(v,Integer.valueOf(fx_id_up.getText()));
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Vehicule has been UPDATED successfully!");
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
