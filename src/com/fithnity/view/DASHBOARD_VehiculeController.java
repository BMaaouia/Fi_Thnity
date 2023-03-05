/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.view;


import com.fithnity.services.ServiceVehicule;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import com.fithnity.entities.Vehicule;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DASHBOARD_VehiculeController implements Initializable {

    @FXML
    private Button fx_back;
    @FXML
    private Button fx_add;
    @FXML
    private Button fx_delete;
    @FXML
    private Button fx_update;
    @FXML
    private AnchorPane vehicule_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceVehicule vs = new ServiceVehicule();
        System.out.println(vs.getAllVehicule().toString());
        
        TableView table = new TableView();
        
        ObservableList data = getInitialTableData();
        table.setItems(data);

       
        
        TableColumn modeleCol = new TableColumn("MODELE");
        modeleCol.setCellValueFactory(new PropertyValueFactory("modele"));

        TableColumn immatriculationCol = new TableColumn("IMMATRICULATION");
        immatriculationCol.setCellValueFactory(new PropertyValueFactory("immatriculation"));
     

        TableColumn categorieCol = new TableColumn("CATEGORIE");
        categorieCol.setCellValueFactory(new PropertyValueFactory("categorie"));

        TableColumn etatCol = new TableColumn("ETAT");
        etatCol.setCellValueFactory(new PropertyValueFactory("etat"));

        table.getColumns().setAll( modeleCol, immatriculationCol, categorieCol,etatCol);
        table.setPrefWidth(565);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        vehicule_list.getChildren().add(table);
        
         fx_update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToModifPersonne(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         
         
             fx_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewPersonne(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
               
                     fx_delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToSuppression(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
                         
                     fx_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
              redirectToMyGallerie(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }
        public void redirectToModifPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/UPDATE_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
        public void redirectToNewPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADD_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         public void redirectToSuppression(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DEL_Vehicule.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
         public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com//fithnity/view/DASHBOARD_Vehicule.fxml"));
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
  
    }    
    

