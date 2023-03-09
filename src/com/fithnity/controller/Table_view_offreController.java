/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import com.fithnity.utils.Pdf;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.fithnity.entity.Offre;
import com.fithnity.service.ServiceOffre;
import com.fithnity.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Table_view_offreController implements Initializable {
  private TableView table;
    private ObservableList data;
    @FXML
    private AnchorPane offre_list;
    @FXML
    private Button fx_update_offre;
    @FXML
    private Button fx_ajouter_offre;
    @FXML
    private Button fx_delete_offre;
    private TableView<Offre> id_liste;
    private TableColumn<Offre, String> id_metier;
    private TableColumn<Offre, String> id_secteur;
    private TableColumn<Offre, String> id_v;
    private TableColumn<Offre, String> id_nbre_poste;
    private TableColumn<Offre, String> id_salaire;
    private TableColumn<Offre, Date> id_date;
    @FXML
    private Button id_liste_offre;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Offre Offre = null ;
    @FXML
    private ListView<Offre> id_liste2;
    @FXML
    private TextField p;
    @FXML
    private Button btn_acceuil;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_blog;
    @FXML
    private Button vers_dashboard_demande;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //**************************
        ServiceOffre so = new ServiceOffre();
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) so.getAllOffre();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste2.setItems(observableList);
        //*****************
        ImageView imageView = new ImageView(getClass().getResource("/images/2222.png").toExternalForm());
  
           id_liste_offre.setGraphic(imageView);
           id_liste_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               listeoffre();
            }
        });
            fx_ajouter_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewOffre(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
               fx_update_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToUpdateoffre(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
               
               
              
    }
                
                 
        public void redirectToNewOffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/ADD_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
           public void redirectToUpdateoffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/UPDATE_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
           
         
    
         

   

    @FXML
    private void listeoffre() {
        ServiceOffre so = new ServiceOffre();
         ArrayList arrayList = null;
        

         arrayList = (ArrayList) so.getAllOffre();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste.setItems(observableList);
        id_metier.setCellValueFactory(new PropertyValueFactory<>("metier"));
        id_secteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));
        id_v.setCellValueFactory(new PropertyValueFactory<>("ville"));
        id_nbre_poste.setCellValueFactory(new PropertyValueFactory<>("Nombredeposte"));
        id_salaire.setCellValueFactory(new PropertyValueFactory<>("salaire"));
        id_date.setCellValueFactory(new PropertyValueFactory<>("dateOffre"));

        
    }
    

    
    @FXML
    private void deleteoffre(ActionEvent event) throws IOException { 
		 ServiceOffre e = new ServiceOffre();
     Offre selectedItem2 = id_liste2.getSelectionModel().getSelectedItem();
  
    id_liste2.getItems().remove(selectedItem2);
      e.delete(selectedItem2);  
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation suprimée avec succés!");
        alert.show();
      
		
	}
   
    
    

    @FXML
    private void update_offre(ActionEvent event) {
        int id = Integer.valueOf(id_liste2.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));

            ServiceOffre e = new ServiceOffre();
            e.update(Offre);

            id_liste2.getItems().removeAll(id_liste2.getSelectionModel().getSelectedItem());
    
        
    
    }

    @FXML
    private void recherche(KeyEvent event) {
        String searchTerm = p.getText();
    ServiceOffre so = new ServiceOffre();
    ObservableList<Offre> list = so.search2(searchTerm);
    List<Offre> filteredList = list.stream()
        .filter(entretient -> entretient.getMetier().toLowerCase().contains(searchTerm.toLowerCase()))
        .collect(Collectors.toList());

       id_liste2.setItems(FXCollections.observableArrayList(filteredList));
    }

   @FXML
    private void pdf(ActionEvent event) {
              Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("Liste des offres");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceOffre.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void vers_dashboard_demande(ActionEvent event) throws IOException {
           Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
    }

 

       

    
    

