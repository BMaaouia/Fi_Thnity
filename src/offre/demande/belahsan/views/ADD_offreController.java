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
import offre.demande.belahsan.entities.Offre;
import offre.demande.belahsan.services.ServiceDemande;
import offre.demande.belahsan.services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class ADD_offreController implements Initializable {

    @FXML
    private TextField fx_metier;
    @FXML
    private TextField fx_secteur;
    @FXML
    private TextField fx_ville;
    @FXML
    private TextField fx_nbreposte;
    @FXML
    private TextField fx_voiture;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private Button fx_button_ajout_offre;
    @FXML
    private TextField fx_duree;
    @FXML
    private TextField fx_salaire;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        fx_button_ajout_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_offre();
            }
        });
    }
    

    @FXML
    private void ajouter_offre() {
  ServiceOffre o_Service = new ServiceOffre();
        Offre o = new Offre();
        
        if(fx_metier.getText().isEmpty()|| fx_secteur.getText().isEmpty()||fx_ville.getText().isEmpty()||fx_nbreposte.getText().isEmpty()||fx_voiture.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        o.setMetier(String.valueOf(fx_metier.getText()));
        o.setSecteur(String.valueOf(fx_secteur.getText()));
        o.setVille(String.valueOf(fx_ville.getText()));
        o.setNombredeposte(String.valueOf(fx_nbreposte.getText()));
        o.setVoiture(String.valueOf(fx_voiture.getText()));
        o.setDuree(String.valueOf(fx_duree.getText()));
        o.setSalaire(String.valueOf(fx_salaire.getText()));

        
        o_Service.ajouter(o);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your OFFRE has been ADDED successfully!");
              alert.show();
    
      fx_afiche_offrePage.setOnAction(new EventHandler<ActionEvent>() {
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

  
         
       
    
}
