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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import offre.demande.belahsan.entities.Demande;
import offre.demande.belahsan.services.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Delete_demandeController implements Initializable {

    @FXML
    private TextField fx_suppresion_id;
    @FXML
    private Button fx_valider_suppresion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         fx_valider_suppresion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               supprimer_demande();
            }
        });
    }    

    @FXML
    private void supprimer_demande() {
        Demande d=new Demande();
        ServiceDemande ds = new ServiceDemande();
        ds.supprimer(Integer.valueOf(fx_suppresion_id.getText()));
    }
    }

