/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.entity.Employée;
import com.fithnity.service.ServiceEmployée;
import com.fithnity.service.ServiceVehicule;
import com.fithnity.utils.Pdf_1;
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
    private ListView table;
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
    @FXML
    private AnchorPane container;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button dashboard_vehicule;
    @FXML
    private Button btn_reclamation;
    @FXML
    private Button btn_reservation;
    @FXML
    private Button btn_offre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
          
        ServiceEmployée es = new ServiceEmployée();
        System.out.println(es.getAllemployée().toString());
        
       table = new ListView();
        
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

       // table.getColumns().setAll( nameCol, lastanameCol, emailCol,addressCol);
        table.setPrefWidth(700);
        table.setPrefHeight(376);
      //  table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        employée_list.getChildren().add(table);
        
         
         
         
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
                     
                     
                     
                     table.setOnMouseClicked(event ->{
                            ServiceEmployée e_Service = new ServiceEmployée();
                            Employée e1= (Employée) table.getSelectionModel().getSelectedItem();
                            e1.getId_employee();
                            firstname_text.setText(e1.getFirstname_employee());
                            lastname_text.setText(e1.getLastname_employee());
                            email_text.setText(e1.getEmail_employee());
                            address_text.setText(e1.getAddress_employee());
                            fx_update.setOnAction(e->{
                            if(firstname_text.getText().isEmpty()||lastname_text.getText().isEmpty()||email_text.getText().isEmpty()||address_text.getText().isEmpty()){
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Erreur");
                                    alert.setHeaderText("ajouter des champs");

                                    alert.showAndWait();
                            }else{

                            
                                try {
                                    e1.setFirstname_employee(String.valueOf(firstname_text.getText()));
                                    e1.setLastname_employee(String.valueOf(lastname_text.getText()));
                                    e1.setEmail_employee(String.valueOf(email_text.getText()));
                                    e1.setAddress_employee(String.valueOf(address_text.getText()));
                                    
                                    e_Service.updateEmployée(e1,Integer.valueOf(e1.getId_employee()));
                                    
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Information Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Your Employe has been UPDATED successfully!");
                                    alert.show();

Parent root2 = FXMLLoader .load(getClass().getResource("/com/fithnity/view/DASHBOARD_employée.fxml"));
Stage window = (Stage) fx_update.getScene().getWindow();
window.setScene(new Scene(root2));
                                } catch (IOException ex) {
                                    Logger.getLogger(DASHBOARD_employéeController.class.getName()).log(Level.SEVERE, null, ex);
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
        
        public void redirectToNewPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Ajout_employée.fxml"));
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
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_employée.fxml"));
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
            e.deleteemployée(e1.getId_employee());

            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
    }

   @FXML
    private void printAction(ActionEvent event) {
         Pdf_1 pd=new Pdf_1();
        try{
        pd.GeneratePdf("Liste des Employées");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceEmployée.class.getName()).log(Level.SEVERE, null, ex);
            }
//        Pdf epdf = new Pdf();
//        epdf.pdfs();
    }  

    @FXML
    private void recherche(KeyEvent event) {
           String searchTerm = p.getText();
    ServiceEmployée su = new ServiceEmployée();
    ObservableList<Employée> list = su.search2(searchTerm);
    List<Employée> filteredList = list.stream()
        .filter(entretient -> entretient.getFirstname_employee().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

       table.setItems(FXCollections.observableArrayList(filteredList));
    }

    @FXML
    private void dashboard_vehicule(ActionEvent event) {
        Parent page1;
        try {
            page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/DASHBOARD_vehicule.fxml"));
            Scene scene = new Scene(page1);
                              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                              stage.setScene(scene);
                              stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DASHBOARD_employéeController.class.getName()).log(Level.SEVERE, null, ex);
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
    private void go_offreback(ActionEvent event) {
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/commands.fxml"));
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


       
    

