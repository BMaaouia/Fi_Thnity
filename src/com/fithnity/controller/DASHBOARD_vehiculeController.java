/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Employée;
import com.fithnity.entity.Vehicule;
import com.fithnity.service.ServiceEmployée;
import com.fithnity.service.ServiceVehicule;
import com.fithnity.utils.Pdf;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DASHBOARD_vehiculeController implements Initializable {

    @FXML
    private AnchorPane vehicule_list;
    @FXML
    private AnchorPane véhicule_list;
    
    private ListView table;
    private ObservableList data;
    @FXML
    private TextField T;
    @FXML
    private TextField modèle_text;
    @FXML
    private TextField catégorie_text;
    @FXML
    private TextField immatriculation_text;
    @FXML
    private TextField etat_text;
    @FXML
    private Button fx_addv;
    @FXML
    private Button fx_updatev;
    @FXML
    private Button fx_deletev;
    @FXML
    private Button fx_backv;
    @FXML
    private AnchorPane container;
    @FXML
    private Button dashboard_employé;
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
            
        ServiceVehicule vs = new ServiceVehicule();
        System.out.println(vs.getAllVehicule().toString());
        
       table = new ListView();
        
         data = getInitialTableData();
        table.setItems(data);

      
        
        TableColumn nameCol = new TableColumn("Modèle");
        nameCol.setCellValueFactory(new PropertyValueFactory("modele"));

        TableColumn lastanameCol = new TableColumn("Immatriculation");
        lastanameCol.setCellValueFactory(new PropertyValueFactory("immatriculation"));
     

        TableColumn emailCol = new TableColumn("Catégorie");
        emailCol.setCellValueFactory(new PropertyValueFactory("categorie"));

        TableColumn addressCol = new TableColumn("Etat");
        addressCol.setCellValueFactory(new PropertyValueFactory("etat"));

       // table.getColumns().setAll( nameCol, lastanameCol, emailCol,addressCol);
        table.setPrefWidth(700);
        table.setPrefHeight(376);
      //  table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        véhicule_list.getChildren().add(table);
        
         
         
         
             fx_addv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewVehicule(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
               
           
                         
                     fx_backv.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
              redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
                     
                     
                     
                     table.setOnMouseClicked(event ->{
                            ServiceVehicule v_Service = new ServiceVehicule();
                            Vehicule v1= (Vehicule) table.getSelectionModel().getSelectedItem();
                            v1.getId();
                            modèle_text.setText(v1.getModele());
                            catégorie_text.setText(v1.getCategorie());
                            immatriculation_text.setText(v1.getImmatriculation());
                            etat_text.setText(String.valueOf(v1.getEtat()));
                            fx_updatev.setOnAction(e->{
                            if(modèle_text.getText().isEmpty()||catégorie_text.getText().isEmpty()||immatriculation_text.getText().isEmpty()||etat_text.getText().isEmpty()){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur");
                                    alert.setHeaderText("ajouter des champs");

                                    alert.showAndWait();
                            }else{

                            
                                try {
                                    v1.setModele(String.valueOf(modèle_text.getText()));
                                    v1.setCategorie(String.valueOf(catégorie_text.getText()));
                                    v1.setImmatriculation(String.valueOf(immatriculation_text.getText()));
                                   v1.setEtat(Boolean.parseBoolean(etat_text.getText()));
                                                                                          ;
                                    
                                    v_Service.updateVehicule(v1,Integer.valueOf(v1.getId()));
                                    
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Your Vehicule has been UPDATED successfully!");
                                    alert.show();

                                    Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
                                    Stage window = (Stage) fx_updatev.getScene().getWindow();
                                    window.setScene(new Scene(root2));
                                } catch (IOException ex) {
                                    Logger.getLogger(DASHBOARD_vehiculeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            });
                     });
        // TODO
    }
//        public void redirectToModifPersonne(ActionEvent event) throws Exception {
//        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/update_employée.fxml"));
//        Scene scene = new Scene(page1);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    }
        
        public void redirectToNewVehicule(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADD_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         
         public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceVehicule vs = new ServiceVehicule();
        list = vs.getAllVehicule();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }

        // TODO
    

    @FXML
    private void DeleteEmploye(ActionEvent event) {
        Vehicule v1 = new Vehicule();
        v1= (Vehicule) table.getSelectionModel().getSelectedItem();
        
            ServiceVehicule v = new ServiceVehicule();
            v.deleteVehicule(v1.getId());

            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

   

    @FXML
    private void recherche(KeyEvent event) {
          String searchTerm = T.getText();
    ServiceVehicule su = new ServiceVehicule();
    ObservableList<Vehicule> list = su.search1(searchTerm);
    List<Vehicule> filteredList = list.stream()
        .filter(entretient -> entretient.getModele().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

       table.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void dashboard_employé(ActionEvent event) {
        Parent page1;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_employée.fxml"));
            Scene scene = new Scene(page1);
                              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setScene(scene);
                              stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DASHBOARD_employéeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
