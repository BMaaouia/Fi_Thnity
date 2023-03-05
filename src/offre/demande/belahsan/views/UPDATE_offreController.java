/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.views;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

    private TextField id_metier_up;
    private TextField id_sec_up;
    private TextField id_ville_up;
    @FXML
    private Button btn_modif_offre;
    private Button fx_retour_home;
    private TextField id_off_up;
    private TextField id_nbrposte_up;
    private TextField id_voiture_up;
    private TextField id_duree_up;
    private TextField id_salaire_up;
    @FXML
    private TextField fx_nbreposte;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private TextField fx_salaire;
    @FXML
    private ComboBox<String> id_metier;
    @FXML
    private ComboBox<String> id_ville;
    @FXML
    private DatePicker Offre_date;
    @FXML
    private TextField fx_secteur;

    /**
     * Initializes the controller class.
     */
  
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules", "Agent de service client");
    id_metier.setItems(list);
    
    ObservableList<String> liste = FXCollections.observableArrayList("Tunis","Bizerte", "Gabès", "Gafsa", "Hammam-Lif", "Jendouba","Kairouan","Kasserine","Sousse");
    id_ville.setItems(liste);
    // initialisation du DatePicker
    LocalDate date = LocalDate.now(); // date par défaut : aujourd'hui
    Offre_date.setValue(date);
        // TODO
               btn_modif_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               update_offre();
            }
        });
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
        
        o.setNombredeposte(String.valueOf(fx_nbreposte.getText()));
        o.setSalaire(String.valueOf(fx_salaire.getText()));
        o.setSecteur(String.valueOf(fx_secteur.getText()));

            String M= id_metier.getSelectionModel().getSelectedItem(); 
            String V= id_ville.getSelectionModel().getSelectedItem();   
            o.setMetier(M);
            o.setVille(V);
            LocalDate dateOffre = Offre_date.getValue();
            o.setdateOffre(dateOffre);

        
        o_Service.modifier(o);
        
       
        
        
        
        
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

    
    
    
    
    
   