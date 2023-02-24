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
public class Ajout_employéeController implements Initializable {

    @FXML
    private TextField firstname_text;
    @FXML
    private TextField lastname_text;
    @FXML
    private TextField email_text;
    @FXML
    private TextField address_text;
    @FXML
    private Button fx_ajout;
    @FXML
    private Button fx_back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         fx_ajout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_emp();
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
    private void ajouter_emp( ) {
         ServiceEmployée e_Service = new ServiceEmployée();
        Employée e = new Employée();
        
         if(firstname_text.getText().isEmpty()|| lastname_text.getText().isEmpty()||email_text.getText().isEmpty()||address_text.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
                 }else{
        
        e.setFirstname_employée(String.valueOf(firstname_text.getText()));
        e.setLastname_employée(String.valueOf(lastname_text.getText()));
        e.setEmail_employée(String.valueOf(email_text.getText()));
        e.setAddress_employée(String.valueOf(address_text.getText()));
        
        e_Service.ajout_employée(e);
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Your Employée has been ADDED successfully!");
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


    
    
