/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inerfaces;

import Services.produitService;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entity.produit;
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
 * @author DELL
 */
public class Gallerie_produitController implements Initializable {
    private TableView table;
    private ObservableList data;
    @FXML
    private Button fx_ajouter_personnePage;
    @FXML
    private Button fx_modif_personnePage;
    @FXML
    private Button fx_delete_personnePage;
    @FXML
    private AnchorPane produit_list;
    @FXML
    private Button nosR;
   
   
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
        idCol.setCellValueFactory(new PropertyValueFactory("id_produit"));

        TableColumn prixCol = new TableColumn("NOM");
        prixCol.setCellValueFactory(new PropertyValueFactory("nom_produit"));
     //   prixCol.setPrefWidth(10);

        TableColumn etatCol = new TableColumn("Poids");
        etatCol.setCellValueFactory(new PropertyValueFactory("poids"));

        TableColumn adresseCol = new TableColumn("description");
        adresseCol.setCellValueFactory(new PropertyValueFactory("description"));

        table.getColumns().setAll(idCol, prixCol, etatCol, adresseCol);
        table.setPrefWidth(565);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        produit_list.getChildren().add(table);
        
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
               nosR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                   to_prodiose(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
               
                     fx_delete_personnePage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToSuppression(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
        // TODO
    }
        public void redirectToModifPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/ModifierProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
        public void redirectToNewPersonne(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/AjoutProduit.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
       
         public void redirectToSuppression(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/SupressionProduit.fxml"));
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
       public void to_prodiose(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/Interfaces/Gallerie_reservation.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();}
  
}
