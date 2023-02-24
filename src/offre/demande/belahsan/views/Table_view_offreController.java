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
import offre.demande.belahsan.services.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class Table_view_offreController implements Initializable {

    @FXML
    private AnchorPane offre_list;
    @FXML
    private Button fx_ajouter_offre;
    @FXML
    private Button fx_update_offre;
    @FXML
    private Button fx_delete_offre;

    /**
     * Initializes the controller class.
     */
   
        // TODO
        private TableView table;
        private ObservableList data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceOffre o = new ServiceOffre();
        System.out.println(o.getAllOffre().toString());
        
        table = new TableView();
        data = getInitialTableData();
        table.setItems(data);

        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("id_offre"));

        TableColumn prixCol = new TableColumn("Metier");
        prixCol.setCellValueFactory(new PropertyValueFactory("metier"));
     //   prixCol.setPrefWidth(10);
        TableColumn prixCo = new TableColumn("secteur");
        prixCo.setCellValueFactory(new PropertyValueFactory("secteur"));
       
        TableColumn adresseCo2 = new TableColumn("ville");
        adresseCo2.setCellValueFactory(new PropertyValueFactory("ville"));

        TableColumn adresseCol = new TableColumn("Nombredeposte");
        adresseCol.setCellValueFactory(new PropertyValueFactory("Nombredeposte"));
        
        TableColumn prixCola = new TableColumn("voiture");
        prixCola.setCellValueFactory(new PropertyValueFactory("voiture"));
       
        TableColumn prixCola2 = new TableColumn("Duree");
        prixCola2.setCellValueFactory(new PropertyValueFactory("Duree"));
        
        TableColumn prixCol3 = new TableColumn("salaire");
        prixCol3.setCellValueFactory(new PropertyValueFactory("salaire"));
        
        table.getColumns().setAll(idCol, prixCol,prixCo, adresseCo2 ,adresseCol , prixCola,prixCola2,prixCol3);
        table.setPrefWidth(600);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        offre_list.getChildren().add(table);
    

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
                        
            fx_delete_offre.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    redirectToDELoffre(event);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }   
        public void redirectToNewOffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/ADD_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
           public void redirectToUpdateoffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/UPDATE_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
           
         public void redirectToDELoffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/DELETE_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
       private ObservableList getInitialTableData() {
        List list = new ArrayList();
        ServiceOffre o = new ServiceOffre();
        list = o.getAllOffre();
        ObservableList data = FXCollections.observableList(list);
        return data;
    }
    
    
}
    

