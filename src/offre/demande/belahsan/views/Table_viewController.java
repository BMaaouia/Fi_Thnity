/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.views;

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
import offre.demande.belahsan.services.ServiceDemande;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Table_viewController implements Initializable {

    @FXML
    private AnchorPane demande_list;
    @FXML
    private Button fx_ajouter_dem;
    @FXML
    private Button fx_update_dem;
    @FXML
    private Button fx_delete_dem;

    
    private TableView table;
    private ObservableList data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceDemande ps = new ServiceDemande();
        System.out.println(ps.getAllDemande().toString());
        
        table = new TableView();
        data = getInitialTableData();
        table.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id"));

        TableColumn prixCol = new TableColumn("NOM");
        prixCol.setCellValueFactory(new PropertyValueFactory("nom"));
     //   prixCol.setPrefWidth(10);
        TableColumn prixCo = new TableColumn("Prenom");
        prixCo.setCellValueFactory(new PropertyValueFactory("prenom"));
       
        TableColumn adresseCo2 = new TableColumn("email");
        adresseCo2.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn adresseCol = new TableColumn("CIN");
        adresseCol.setCellValueFactory(new PropertyValueFactory("cin"));
        
        TableColumn prixCola = new TableColumn("TEL");
        prixCola.setCellValueFactory(new PropertyValueFactory("tel"));
        
        
        
        table.getColumns().setAll(idCol, prixCol,prixCo, adresseCo2 ,adresseCol , prixCola);
        table.setPrefWidth(565);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        demande_list.getChildren().add(table);
        
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
                    redirectToDELdem(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }   
        public void redirectToNewDemande(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/add_demande.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
           public void redirectToUpdatedem(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/UPDATE_Dem.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
           
         public void redirectToDELdem(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/Delete_dem.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceDemande ps = new ServiceDemande();
        list = ps.getAllDemande();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
    
    
}
