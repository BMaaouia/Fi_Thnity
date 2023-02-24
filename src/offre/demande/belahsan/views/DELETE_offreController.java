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
public class DELETE_offreController implements Initializable {

    @FXML
    private TextField id_o_del;
    @FXML
    private Button btn_suppression_offre;
    @FXML
    private Button fx_retour_acceuil;

    /**
     * Initializes the controller class.
     */
    
         @Override
    public void initialize(URL url, ResourceBundle rb) {
        
             btn_suppression_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               DEL_offre();
            }
        });
     
    fx_retour_acceuil.setOnAction(new EventHandler<ActionEvent>() {
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
    }    
    @FXML
    private void DEL_offre() {
         Offre o=new Offre();
                
                  if(id_o_del.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        ServiceOffre ps = new ServiceOffre ();
       // p.setId_produit(Integer.valueOf(id_produit_sup.getText()));
        ps.supprimer(Integer.valueOf(id_o_del.getText()));
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your Offre has been DELETED successfully!");
              alert.show();
        
        
    } }
    
            public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    } 

}
    
    
