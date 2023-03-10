/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.fithnity.entity.Demande;
import com.fithnity.entity.Offre;
import com.fithnity.service.ServiceDemande;
import com.fithnity.service.ServiceOffre;
import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Table_viewController implements Initializable {

    private AnchorPane demande_list;
    @FXML
    private Button fx_ajouter_dem;
    @FXML
    private Button fx_update_dem;
    @FXML
    private Button fx_delete_dem;

    
    private TableView table;
    private ObservableList data;
    @FXML
    private Button id_liste_demande;
    @FXML
    private ListView<Demande> id_liste2;
    Demande Demande = null ;
    @FXML
    private JFXButton download;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button vers_dashboard_offre;
    @FXML
    private Button vers_stat;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_employe;
    @FXML
    private Button btn_reservation;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       ServiceDemande sd= new ServiceDemande();
       
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) sd.getAllDemande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste2.setItems(observableList);
        ImageView imageView = new ImageView(getClass().getResource("/images/2222.png").toExternalForm());
  
           id_liste_demande.setGraphic(imageView);
           id_liste_demande.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               listedemande();
            }
        });
        
        
             fx_ajouter_dem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewDemande(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
               
             fx_update_dem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToUpdatedem(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
                        
            fx_delete_dem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    deletedemande(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }   
        public void redirectToNewDemande(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/commands.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
           public void redirectToUpdatedem(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/UPDATE_Dem.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
//           
//         public void redirectToDELdem(ActionEvent event) throws Exception {
//        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Delete_dem.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }
        
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceDemande ps = new ServiceDemande();
        list = ps.getAllDemande();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
@FXML
    private void update_demande(ActionEvent event) throws SQLException {
        int id = Integer.valueOf(id_liste2.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));

            ServiceDemande d = new ServiceDemande();
            d.update(Demande);

            id_liste2.getItems().removeAll(id_liste2.getSelectionModel().getSelectedItem());
    
        
    
    }


    @FXML
    private void deletedemande(ActionEvent event) throws IOException {
        ServiceDemande e = new ServiceDemande();
        Demande selectedItem2 = id_liste2.getSelectionModel().getSelectedItem();

        id_liste2.getItems().remove(selectedItem2);
        e.deletes(selectedItem2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("DEMANDE suprimée avec succés!");
        alert.show();


    }
    @FXML
    private void listedemande() {
      
        ServiceDemande sd = new ServiceDemande();
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) sd.getAllDemande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste2.setItems(observableList);
//        id_metier.setCellValueFactory(new PropertyValueFactory<>("metier"));
//        id_secteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));
//        id_v.setCellValueFactory(new PropertyValueFactory<>("ville"));
//        id_nbre_poste.setCellValueFactory(new PropertyValueFactory<>("Nombredeposte"));
//        id_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
//        id_date.setCellValueFactory(new PropertyValueFactory<>("dateOffre"));

        
    }
    @FXML
    private void downloadListViewContents() {

        // Show a file chooser dialog to let the user select where to save the file
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(id_liste2.getScene().getWindow());

        if (file != null) {

            try {
                // Open a FileWriter on the selected file and write the ListView contents to it
                FileWriter writer = new FileWriter(file);
                ObservableList<com.fithnity.entity.Demande> items = id_liste2.getItems();
                for (com.fithnity.entity.Demande item : items) {
                    writer.write(item + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void vers_dashboard_offre(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        
        
    }

    @FXML
    private void vers_stat(ActionEvent event) throws IOException {
           Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/StatisticsView.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void go_userback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/AdminPanel.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reclamationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/reclamationback.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_blogback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_Blog.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_employeback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_employée.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_reservationback(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADDlivraison.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void go_acceuil(ActionEvent event) throws IOException {
          Parent root3 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/Acceuil.fxml"));
    Stage window = (Stage) btn_acceuil.getScene().getWindow();
    window.setScene(new Scene(root3));
    }



}
    
    

