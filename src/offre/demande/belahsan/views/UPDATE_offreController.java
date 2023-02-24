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
import offre.demande.belahsan.entities.Offre;
import offre.demande.belahsan.services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class UPDATE_offreController implements Initializable {

    @FXML
    private TextField id_metier_up;
    @FXML
    private TextField id_sec_up;
    @FXML
    private TextField id_ville_up;
    @FXML
    private Button btn_modif_offre;
    @FXML
    private Button fx_retour_home;
    @FXML
    private TextField id_off_up;
    @FXML
    private TextField id_nbrposte_up;
    @FXML
    private TextField id_voiture_up;
    @FXML
    private TextField id_duree_up;
    @FXML
    private TextField id_salaire_up;

    /**
     * Initializes the controller class.
     */
  
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               btn_modif_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               update_offre();
            }
        });
                      fx_retour_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToAcceuil(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }    

    @FXML
    private void update_offre() {
              ServiceOffre o_Service = new ServiceOffre();
        Offre o = new Offre();
        
        if(id_metier_up.getText().isEmpty()|| id_sec_up.getText().isEmpty()||id_ville_up.getText().isEmpty()||id_off_up.getText().isEmpty()||id_voiture_up.getText().isEmpty()||id_duree_up.getText().isEmpty()||id_salaire_up.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        o.setId_offre(Integer.valueOf(id_off_up.getText()));
        o.setMetier(String.valueOf(id_metier_up.getText()));
        o.setNombredeposte(String.valueOf(id_nbrposte_up.getText()));
        o.setSecteur(String.valueOf(id_sec_up.getText()));
        o.setVille(String.valueOf(id_ville_up.getText()));
        o.setVoiture(String.valueOf(id_voiture_up.getText()));
        o.setDuree(String.valueOf(id_duree_up.getText()));
        o.setSalaire(String.valueOf(id_salaire_up.getText()));
        
        
        o_Service.modifier(o,Integer.valueOf(id_off_up.getText()));
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your OFFRE has been UPDATED successfully!");
              alert.show();
    }}
            public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

}

    
    
    
    
    
   