/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

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
public class ModifierProduitController implements Initializable {

    @FXML
    private TextField nom_produit_modif;
    @FXML
    private TextField poids_produit_modif;
    @FXML
    private TextField description_produit_amodif;
    @FXML
    private Button btn_modif_p;
    @FXML
    private Button fx_gallerie_personnePage;
    @FXML
    private TextField id_produit_modif1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           btn_modif_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               modif_P();
            }
        });
        // TODO
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
    }    
     public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_reservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
     
    @FXML
    private void modif_P() {
       produitService p_Service = new produitService();
       produit p = new produit();
       if(id_produit_modif1.getText().isEmpty()|| nom_produit_modif.getText().isEmpty()||poids_produit_modif.getText().isEmpty()||description_produit_amodif.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
       
        p.setId_produit(Integer.valueOf(id_produit_modif1.getText()));
        p.setNom_produit(String.valueOf(nom_produit_modif.getText()));
        p.setPoids(Integer.valueOf(poids_produit_modif.getText()));
        p.setDescription(String.valueOf(description_produit_amodif.getText()));
        
        p_Service.updateProduit(p,Integer.valueOf(id_produit_modif1.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Your produit has been updated successfully!");
            alert.show();

    }}
    
}
