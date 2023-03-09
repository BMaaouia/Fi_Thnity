/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.utils.Pdf;
import com.fithnity.service.reservationService;
import com.fithnity.entity.reservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ListView;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.C;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mahmo
 */
public class DASHBOARDreservationController implements Initializable {

    @FXML
    private TextField prix_filed;
    @FXML
    private TextField poids_filed;
    @FXML
    private Button supprimer;
    @FXML
    private Button refresh;
    @FXML
    private ComboBox<String> idvd;
    @FXML
    private ComboBox<String> idva;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField rech;
    @FXML
    private Button supp;
    @FXML
    private Button supp1;
    ObservableList<reservation> eventList2;
    @FXML
    private ListView<reservation> id_list2;
    @FXML
    private Button back_reservation;
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        back_reservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    back(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
        reservationService uc =new reservationService();
        ArrayList arrayList = null;
        try {
            arrayList = (ArrayList) uc.getAllReservation();
        } catch (SQLException ex) {
            Logger.getLogger(DASHBOARDreservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
         ObservableList observableList = FXCollections.observableArrayList(arrayList);
         id_list2.setItems(observableList);
        ObservableList<String> list = FXCollections.observableArrayList( "Tunis","Sousse","Hammemt" ) ;
        idvd.setItems(list);
        ObservableList<String> liste = FXCollections.observableArrayList("Tunis","Sousse","Hammemt") ;
        idva.setItems(liste);
//             Refresh();

          
           id_list2.setOnMouseClicked(event->{
            


        reservation current = id_list2.getSelectionModel().getSelectedItem();
       
       
         current.getId_r();

      prix_filed.setText(Float.toString(current.getPrix()));
       poids_filed.setText(Float.toString(current.getPoids()));
       idvd.setValue(current.getVilleDepart());
       idva.setValue(current.getVilleArrive());
    
        supp1.setOnAction(e->{
            
             reservationService o_Service = new reservationService();
     
        
   
       current.setPrix(Float.parseFloat(prix_filed.getText()));
        current.setPoids(Float.parseFloat(poids_filed.getText()));
   
            String M= idvd.getSelectionModel().getSelectedItem().toString(); 
            String V= idva.getSelectionModel().getSelectedItem().toString();   
            current.setVilleDepart(M);
            current.setVilleArrive(V);
            
            try {
                o_Service.update(current);
            } catch (SQLException ex) {
                Logger.getLogger(DASHBOARDreservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Information Dialog");
              alert.setHeaderText(null);
              alert.setContentText("Your reservation has been Modified successfully!");
              alert.show();
   
        Parent root2 = null;
            try {
                root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBOARDreservation.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(DASHBOARDreservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    Stage window = (Stage) supp1.getScene().getWindow();
    window.setScene(new Scene(root2));
        
        });
        });

    }   
        private void handleTableSelection() {
    reservation offreSelectionnee = id_list2.getSelectionModel().getSelectedItem();
    if (offreSelectionnee != null) {
        // Récupérer les données de la ligne sélectionnée
        LocalDate date = offreSelectionnee.getDateReser();
      
        String vd = offreSelectionnee.getVilleDepart();
        String va = offreSelectionnee.getVilleArrive();
        float prix = offreSelectionnee.getPrix();
        float poids= offreSelectionnee.getPoids();

        // Faire quelque chose avec ces données (par exemple les afficher dans une boîte de dialogue)
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information sur l'offre sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText( "\ndate : " + date + "\nvilledepart : " + vd + "\nvilleArrive : " + va + "\nprix : " + prix +
                "\npoids : " + poids );
        alert.showAndWait();
       }
    
    id_list2.setOnMouseClicked(event -> {
    if (event.getClickCount() == 1) { // une seule pression de souris
        handleTableSelection();
    }
});

id_list2.setOnKeyPressed(event -> {
    if (event.getCode().equals(KeyCode.ENTER)) { // appui sur la touche Entrée
        handleTableSelection();
    }
});

}

    @FXML
    private void clear(ActionEvent event) {
    }

//    @FXML
//    private void Refresh() {
//              reservationService uc =new reservationService();
//        ArrayList arrayList = null;
//        try {
//            arrayList = (ArrayList) uc.getAllReservation();
//        } catch (SQLException ex) {
//            Logger.getLogger(DASHBOARDreservationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ObservableList observableList = FXCollections.observableArrayList(arrayList);
//        id_list2.setItems(observableList);
//        id_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        id_poids.setCellValueFactory(new PropertyValueFactory<>("poids"));
//        id_v_a.setCellValueFactory(new PropertyValueFactory<>("villeArrive"));
//        id_v_d.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
//        id_Date.setCellValueFactory(new PropertyValueFactory<>("date_r")); 
//                 id_v_a.setCellValueFactory(cellData -> {
//    reservation entretient = cellData.getValue();
//    if (entretient != null && entretient.getVilleArrive() != null) {
//        String nomOffre = entretient.getVilleArrive();
//        return new SimpleStringProperty(nomOffre);
//    } else {
//        return null;
//    }
//});
//    }
//    

    @FXML
    private void FiltrerKeyReleased3(KeyEvent event) {
           String searchTerm = rech.getText();
    reservationService su = new reservationService();
    ObservableList<reservation> list = su.searchent2(searchTerm);
    List<reservation> filteredList = list.stream()
        .filter(reservation -> reservation.getVilleDepart().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());
   id_list2.setItems(FXCollections.observableArrayList(filteredList));
    }


    @FXML
    private void back(ActionEvent event) throws IOException {
//               Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDreservation.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
         Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/ADDreservation.fxml"));
    Stage window = (Stage) back_reservation.getScene().getWindow();
    window.setScene(new Scene(root3));
    }



    @FXML
    private void Supprimer(ActionEvent event) throws IOException, SQLException { 
		 reservationService uc =new reservationService();
     reservation selectedItem2 = id_list2.getSelectionModel().getSelectedItem();
  
    id_list2.getItems().remove(selectedItem2);
      uc.delete(selectedItem2);  
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reservation suprimée avec succés!");
        alert.show();
       
		
	}
    
    @FXML
    private void pdf(ActionEvent event) {
                     Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des Réservation");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(reservationService.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
