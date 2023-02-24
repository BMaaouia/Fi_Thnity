/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.view;

import com.fithnity.services.ServiceEmployée;
import com.fithnity.services.ServiceVehicule;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DASHBOARD_employéeController implements Initializable {

   
    @FXML
    private Button fx_back;
    @FXML
    private Button fx_add;
    @FXML
    private Button fx_delete;
    @FXML
    private Button fx_update;
    @FXML
    private AnchorPane employée_list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        ServiceEmployée es = new ServiceEmployée();
        System.out.println(es.getAllemployée().toString());
        
        TableView table1 = new TableView();
        
        ObservableList data = getInitialTableData();
        table1.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id_employée"));
        
        
        TableColumn nameCol = new TableColumn("Firstname");
        nameCol.setCellValueFactory(new PropertyValueFactory("Firstname_employée"));

        TableColumn lastanameCol = new TableColumn("Lastname");
        lastanameCol.setCellValueFactory(new PropertyValueFactory("Lastname_employée"));
     

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("Email_employée"));

        TableColumn addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("Address_employée"));

        table1.getColumns().setAll(idCol, nameCol, lastanameCol, emailCol,addressCol);
        table1.setPrefWidth(565);
        table1.setPrefHeight(300);
        table1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        employée_list.getChildren().add(table1);
        
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
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/update_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
        public void redirectToNewPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ajout_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         public void redirectToSuppression(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/supp_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
         public void redirectToMyGallerie(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com//fithnity/view/DASHBOARD_employée.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    
}
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceEmployée es = new ServiceEmployée();
        list = es.getAllemployée();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
  
    }    
    

       
    

