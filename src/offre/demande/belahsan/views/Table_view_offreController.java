/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package offre.demande.belahsan.views;

import java.awt.event.MouseEvent;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import offre.demande.belahsan.entities.Offre;
import offre.demande.belahsan.services.ServiceOffre;
import offre.demande.belahsan.utils.DataSource;

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
    @FXML
    private TableView<Offre> id_liste;
    @FXML
    private TableColumn<Offre, String> id_metier;
    @FXML
    private TableColumn<Offre, String> id_secteur;
    @FXML
    private TableColumn<Offre, String> id_v;
    @FXML
    private TableColumn<Offre, String> id_nbre_poste;
    @FXML
    private TableColumn<Offre, String> id_salaire;
    @FXML
    private TableColumn<Offre, Date> id_date;
    @FXML
    private Button id_liste_offre;
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Offre Offre = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/ADD_offre.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
        
           public void redirectToUpdateoffre(ActionEvent event) throws Exception {
        Parent page1 = FXMLLoader.load(getClass().getResource("/offre/demande/belahsan/views/UPDATE_offre.fxml"));
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
    private void deleteoffre(ActionEvent event) throws SQLException{
         
                            
                            
            int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));

            ServiceOffre e = new ServiceOffre();
            e.supprimer(id);

            id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
            query = "DELETE FROM `offre` WHERE offre_id  ="+Offre.getoffre_id();
                                connection = (Connection) DataSource.getInstance();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                listeoffre();
                                
                            
                            }
    
        
    

    @FXML
    private void deleteoffre(javafx.scene.input.MouseEvent event) {
        
    }

    @FXML
    private void update_offre(ActionEvent event) {
        int id = Integer.valueOf(id_liste.getSelectionModel().getSelectedItem().toString().split("=")[1].substring(0, 1));

            ServiceOffre e = new ServiceOffre();
            e.modifier(Offre);

            id_liste.getItems().removeAll(id_liste.getSelectionModel().getSelectedItem());
    
        
    
    }

    @FXML
    private void UPDATEOFFRE(javafx.scene.input.MouseEvent event) {
        
    }
}
       

    
    

