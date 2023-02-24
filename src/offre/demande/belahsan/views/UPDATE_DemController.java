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
public class UPDATE_DemController implements Initializable {

    @FXML
    private TextField id_nom_up;
    @FXML
    private TextField id_prenom_up;
    @FXML
    private TextField id_tel_up;
    @FXML
    private Button btn_modif_dem;
    @FXML
    private Button fx_retour_home;
    @FXML
    private TextField id_dem_up;
    @FXML
    private TextField id_mail_up;
    @FXML
    private TextField id_cin_up;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
               btn_modif_dem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               updateDEM();
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
    private void updateDEM() {
              ServiceDemande d_Service = new ServiceDemande();
        Demande d = new Demande();
        
          if(id_nom_up.getText().isEmpty()|| id_prenom_up.getText().isEmpty()||id_tel_up.getText().isEmpty()||id_dem_up.getText().isEmpty()||id_mail_up.getText().isEmpty()||id_cin_up.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        d.setId(Integer.valueOf(id_dem_up.getText()));
        d.setNom(String.valueOf(id_nom_up.getText()));
        d.setPrenom(String.valueOf(id_prenom_up.getText()));
        d.setCin(String.valueOf(id_cin_up.getText()));
        d.setEmail(String.valueOf(id_mail_up.getText()));
        d.setNumeroTelephone(String.valueOf(id_tel_up.getText()));
        d_Service.modifier(d,Integer.valueOf(id_dem_up.getText()));
        
        
        
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your Demande has been UPDATED successfully!");
              alert.show();
    }}
            public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

}
