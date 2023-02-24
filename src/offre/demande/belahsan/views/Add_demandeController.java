/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.views;

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
import offre.demande.belahsan.entities.Demande;
import offre.demande.belahsan.services.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Add_demandeController implements Initializable {

    @FXML
    private TextField fx_nom;
    @FXML
    private TextField fx_prenom;
    @FXML
    private TextField fx_cin;
    @FXML
    private TextField fx_email;
    @FXML
    private TextField fx_tel;
    @FXML
    private Button fx_button_ajout_demande;
    @FXML
    private Button fx_afiche_demandePage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fx_button_ajout_demande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_demande();
            }
        });
    }    
    @FXML
    private void ajouter_demande() {
        ServiceDemande d_Service = new ServiceDemande();
        Demande d = new Demande();
        
        if(fx_nom.getText().isEmpty()|| fx_prenom.getText().isEmpty()||fx_cin.getText().isEmpty()||fx_email.getText().isEmpty()||fx_tel.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        d.setNom(String.valueOf(fx_nom.getText()));
        d.setPrenom(String.valueOf(fx_prenom.getText()));
        d.setCin(String.valueOf(fx_cin.getText()));
        d.setEmail(String.valueOf(fx_email.getText()));
        d.setNumeroTelephone(String.valueOf(fx_tel.getText()));
        
        d_Service.ajouter(d);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your Demande has been ADDED successfully!");
              alert.show();
    
      fx_afiche_demandePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToAcceuil(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }  } 
         public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

  
         
       
    
}
