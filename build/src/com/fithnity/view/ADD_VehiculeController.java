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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ADD_VehiculeController implements Initializable {

    @FXML
    private TextField fx_model;
    @FXML
    private TextField fx_mat;
    @FXML
    private TextField fx_cat;
    @FXML
    private CheckBox fx_etat;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button fx_back;
    @FXML
    private CheckBox fx_etat2;
    @FXML
    private Label fx_etatLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_v();
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
    }
    

    @FXML
    private void ajouter_v() {
        ServiceVehicule V_Service = new ServiceVehicule();
        Vehicule v = new Vehicule();
        if(fx_model.getText().isEmpty() || fx_mat.getText().isEmpty() || fx_cat.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Remplissez tous les champs");
            alert.showAndWait();
        } else {
            
            v.setModele(fx_model.getText());
            v.setImmatriculation(fx_mat.getText());
            v.setCategorie(fx_cat.getText());
            v.setEtat(fx_etat.isSelected());
            v.setEtat(fx_etat2.isSelected());
            
          if(fx_etat.isSelected()) {
    fx_etatLabel.setText("Neuf");
} else {
    fx_etatLabel.setText("Occasion");
}
            
            try {
                V_Service.addVehicule(v);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Le véhicule a été ajouté avec succès !");
                alert.show();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Une erreur est survenue lors de l'ajout du véhicule : " + e.getMessage());
                alert.show();
            }
        }
    }


    public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com//fithnity/view/DASHBOARD_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
}
