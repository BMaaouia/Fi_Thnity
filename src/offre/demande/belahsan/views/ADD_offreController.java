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
import javafx.event.Event;
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
public class ADD_offreController implements Initializable {

    @FXML
    private TextField fx_nbreposte;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private Button fx_button_ajout_offre;
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
        // TODO
        ObservableList<String> list = FXCollections.observableArrayList("Chauffeur-livreur", "Agent administratif", "Gestionnaire des vehicules", "Agent de service client");
    id_metier.setItems(list);
    
    ObservableList<String> liste = FXCollections.observableArrayList("Tunis","Bizerte", "Gabès", "Gafsa", "Hammam-Lif", "Jendouba","Kairouan","Kasserine","Sousse");
    id_ville.setItems(liste);
    // initialisation du DatePicker
    LocalDate date = LocalDate.now(); // date par défaut : aujourd'hui
    Offre_date.setValue(date); // affectation de la date au DatePicker
        
    
        fx_button_ajout_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               ajouter_offre();
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
          Offre o=new Offre();
        Offre_date.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                LocalDate dateOffre = Offre_date.getValue();
               // o.setdateOffre(dateOffre);
            }
        });
                       Offre_date.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Mettre à jour l'affichage ou exécuter d'autres actions en réponse aux changements de date
        });
    }   
        
        
        
        

    @FXML
    private void ajouter_offre() {
       ServiceOffre o_Service = new ServiceOffre();
        Offre o = new Offre();
            //    Offre o = new Offre(id_metier.getSelectionModel().getSelectedItem().toString(),id_ville.getSelectionModel().getSelectedItem().toString(),fx_secteur.getText()),fx_nbreposte.getText(),currentDate,fx_salaire.getText());

        if(fx_nbreposte.getText().isEmpty()|| fx_salaire.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
                alert.setHeaderText("ajouter des champs");
                
                alert.showAndWait();
        }else{
        
        
        o.setNombredeposte(String.valueOf(fx_nbreposte.getText()));
        o.setSalaire(String.valueOf(fx_salaire.getText()));
        o.setSecteur(String.valueOf(fx_secteur.getText()));
            String M= id_metier.getSelectionModel().getSelectedItem().toString(); 
            String V= id_ville.getSelectionModel().getSelectedItem().toString();   
            o.setMetier(M);
            o.setVille(V);
            // ajout d'une réservation
           // LocalDate dateOffre = Offre_date.getValue(); // récupération de la date sélectionnée
           java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
        o.setDateOffre(currentDate);

        
        o_Service.ajouter(o);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your OFFRE has been ADDED successfully!");
              alert.show();



        }
        }

 public void redirectToAcceuil(ActionEvent event) throws Exception {
            Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

  
         
       
    
}

    

