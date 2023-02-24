/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inerfaces;

import Services.produitService;
import entity.produit;
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
 * @author DELL
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField nom_produit_ajout;
    @FXML
    private TextField poids_produit_ajout;
    @FXML
    private TextField description_produit_ajout;
    @FXML
    private Button btn_ajout_p;
    @FXML
    private Button fx_gallerie_personnePage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           btn_ajout_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               addP();
            }
        });
           
                  fx_gallerie_personnePage.setOnAction(new EventHandler<ActionEvent>() {
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
         public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_produit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void addP() {
        produitService p_Service = new produitService();
        produit p = new produit();
        if(nom_produit_ajout.getText().isEmpty()|| poids_produit_ajout.getText().isEmpty()||description_produit_ajout.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        p.setNom_produit(String.valueOf(nom_produit_ajout.getText()));
        p.setPoids(Integer.valueOf(poids_produit_ajout.getText()));
        p.setDescription(String.valueOf(description_produit_ajout.getText()));
        
        p_Service.addProduit(p);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your produit has been created successfully!");
            alert.show();
    }
    }
    
}
