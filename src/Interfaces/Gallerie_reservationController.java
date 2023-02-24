/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Services.produitService;
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
 * @author DELL
 */
public class Gallerie_reservationController implements Initializable {
    private TableView table;
    private ObservableList data;
    @FXML
    private Button fx_ajouter_personnePage;
    @FXML
    private Button fx_modif_personnePage;
    private Button fx_delete_personnePage;
    private AnchorPane produit_list;
    @FXML
    private AnchorPane reservation_list;
    private Button fx_delete_Page;
    @FXML
    private Button fx_delete_Res;
    @FXML
    private Button to_prodios;
   
   
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               produitService ps = new produitService();
        System.out.println(ps.getAllProduit().toString());
        
        table = new TableView();
        data = getInitialTableData();
        table.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id_r"));

        TableColumn prixCol = new TableColumn("prix");
        prixCol.setCellValueFactory(new PropertyValueFactory("prix"));
     //   prixCol.setPrefWidth(10);

        TableColumn poidsCol = new TableColumn("Poids");
        poidsCol.setCellValueFactory(new PropertyValueFactory("poids"));

        TableColumn villeDepartCol = new TableColumn("villeDepart");
        villeDepartCol.setCellValueFactory(new PropertyValueFactory("villeDepart"));
        
        TableColumn villeArriveCol = new TableColumn("villeArrive");
        villeArriveCol.setCellValueFactory(new PropertyValueFactory("villeArrive"));

        TableColumn date_rCol = new TableColumn("date_r");
        date_rCol.setCellValueFactory(new PropertyValueFactory("date_r"));

        TableColumn imageCol = new TableColumn("image");
        imageCol.setCellValueFactory(new PropertyValueFactory("image"));


        table.getColumns().setAll(idCol, prixCol, poidsCol, villeDepartCol,villeArriveCol,date_rCol,imageCol);
        table.setPrefWidth(565);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        reservation_list.getChildren().add(table);
        
            to_prodios.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    to_prodiose(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         fx_modif_personnePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToModifPersonne(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
         
         
        fx_ajouter_personnePage.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToNewPersonne(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
            
               
        fx_delete_Res.setOnAction(new EventHandler<ActionEvent>() {
        @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToSupprimer(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }
        public void redirectToModifPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/modifierReservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
        public void redirectToNewPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AjoutReservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
           public void to_prodiose(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_produit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
         public void redirectToSupprimer(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/supprimerReservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        produitService ps = new produitService();
        list = ps.getAllProduit();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
    
}
