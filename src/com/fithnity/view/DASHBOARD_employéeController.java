/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.view;

import com.fithnity.entities.Employée;
import com.fithnity.services.ServiceEmployée;
import com.fithnity.services.ServiceVehicule;
import com.fithnity.utils.Pdf;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private TableView table;
    private ObservableList data;
    @FXML
    private Button print;
    @FXML
    private TextField p;
    @FXML
    private TextField firstname_text;
    @FXML
    private TextField lastname_text;
    @FXML
    private TextField email_text;
    @FXML
    private TextField address_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          
        ServiceEmployée es = new ServiceEmployée();
        System.out.println(es.getAllemployée().toString());
        
       table = new TableView();
        
         data = getInitialTableData();
        table.setItems(data);

      
        
        TableColumn nameCol = new TableColumn("Firstname");
        nameCol.setCellValueFactory(new PropertyValueFactory("Firstname_employée"));

        TableColumn lastanameCol = new TableColumn("Lastname");
        lastanameCol.setCellValueFactory(new PropertyValueFactory("Lastname_employée"));
     

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("Email_employée"));

        TableColumn addressCol = new TableColumn("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory("Address_employée"));

        table.getColumns().setAll( nameCol, lastanameCol, emailCol,addressCol);
        table.setPrefWidth(898);
        table.setPrefHeight(376);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        employée_list.getChildren().add(table);
        
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

    @FXML
    private void DeleteEmploye(ActionEvent event) {
        //int id = Integer.valueOf(table.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));
        
        Employée e1 = new Employée();
        e1= (Employée) table.getSelectionModel().getSelectedItem();
        
            ServiceEmployée e = new ServiceEmployée();
            e.deleteemployée(e1.getId_employée());

            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

   @FXML
    private void printAction(ActionEvent event) {
        Pdf epdf = new Pdf();
        epdf.pdfs();
    }  

    @FXML
    private void recherche(KeyEvent event) {
           String searchTerm = p.getText();
    ServiceEmployée su = new ServiceEmployée();
    ObservableList<Employée> list = su.search2(searchTerm);
    List<Employée> filteredList = list.stream()
        .filter(entretient -> entretient.getFirstname_employée().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

       table.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void get_employée(MouseEvent event) {
       Employée e1 = new Employée();
        e1= (Employée) table.getSelectionModel().getSelectedItem();
        firstname_text.setText(e1.getFirstname_employée());
        lastname_text.setText(e1.getLastname_employée());
        email_text.setText(e1.getEmail_employée());
        address_text.setText(e1.getAddress_employée());
       
    }
    }


       
    

