/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.view;

import com.fithnity.entities.Employée;
import com.fithnity.services.ServiceEmployée;
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
public class Update_employéeController implements Initializable {

   
    @FXML
    private Button btn_update;
    @FXML
    private Button fx_back;
    @FXML
    private TextField fx_id_up;
    @FXML
    private TextField fx_firstname;
    @FXML
    private TextField fx_lastname;
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           btn_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               update_emp();
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
        // TODO
    }    

    @FXML
    private void update_emp() {
        ServiceEmployée e_Service = new ServiceEmployée();
        Employée e = new Employée();
        
        if(fx_id_up.getText().isEmpty()|| fx_firstname.getText().isEmpty()||fx_lastname.getText().isEmpty()||fx_email.getText().isEmpty()||fx_address.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        e.setId_employée(Integer.valueOf(fx_id_up.getText()));
        e.setFirstname_employée(String.valueOf(fx_firstname.getText()));
        e.setLastname_employée(String.valueOf(fx_lastname.getText()));
        e.setEmail_employée(String.valueOf(fx_email.getText()));
        e.setAddress_employée(String.valueOf(fx_address.getText()));
        
        e_Service.updateEmployée(e,Integer.valueOf(fx_id_up.getText()));
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Vehicule has been UPDATED successfully!");
        alert.show();
    }}
    
     public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com//fithnity/view/DASHBOARD_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
    
}

    
    

