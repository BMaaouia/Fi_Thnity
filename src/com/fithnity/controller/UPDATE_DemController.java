/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.fithnity.entity.Demande;
import com.fithnity.entity.Offre;
import com.fithnity.service.ServiceDemande;
import com.fithnity.service.ServiceOffre;

/**
 * FXML Controller class
 *
 * @author andol
 */
public class UPDATE_DemController implements Initializable {


    private Image image;
    private Image imageCV;
    private Image imageLM;
    String imagePath = null;
    String imagePathForCV=null;
    String imagePathForLM=null;
    public Button fx_afiche_demandePage;
    public Button fx_button_ajout_demande;
    public TextField fx_cin;
    public TextField fx_competences;
    public ImageView iv_img_cv;
    public ImageView iv_img_lettre;
    public ImageView iv_img_grise;
    public ListView id_liste3;
    public Button id_liste_offre;

    private TableView<Demande> id_liste;
    private TableColumn<Demande, String> id_competences;
    private TableColumn<Demande, String> id_cin;
    @FXML
    private Label fx_lettre;
    /* private TextField id_metier_up;
    private TextField id_sec_up;
    private TextField id_ville_up;
    @FXML
    private Button btn_modif_offre;
    private Button fx_retour_home;
    private TextField id_off_up;
    private TextField id_nbrposte_up;
    private TextField id_voiture_up;
    private TextField id_duree_up;
    private TextField id_salaire_up;
    @FXML
    private TextField fx_nbreposte;
    @FXML
    private Button fx_afiche_offrePage;
    @FXML
    private TextField fx_salaire;
    @FXML
    private ComboBox<String> id_metier;
    @FXML
    private ComboBox<String> id_ville;
    @FXML
    private DatePicker Offre_date;
    @FXML
    private TextField fx_secteur;
    @FXML
    private TableView<Offre> id_liste;
    @FXML
    private TableColumn<Offre, String> id_metier2;
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
    @FXML
    private ListView<Offre> id_liste2;

*/
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //**************************
        ServiceDemande so = new ServiceDemande();
        ArrayList arrayList = null;


        arrayList = (ArrayList) so.getAllDemande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        id_liste3.setItems(observableList);


        id_liste3.setOnMouseClicked(event->{


            java.sql.Date currentDate = new java.sql.Date( System.currentTimeMillis() );
            Demande current = (Demande) id_liste3.getSelectionModel().getSelectedItem();

            current.getId();

            fx_competences.setText(current.getcompetences());
            fx_cin.setText(current.getcin());
            fx_button_ajout_demande.setOnAction(e->{

                ServiceDemande o_Service = new ServiceDemande();
                if(fx_competences.getText().isEmpty()|| fx_cin.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("ajouter des champs");

                    alert.showAndWait();
                }else{

                    current.setcin(String.valueOf(fx_cin.getText()));
                    current.setcompetences(String.valueOf(fx_competences.getText()));


                    try {
                        o_Service.updates(current);

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Your OFFRE has been Modified successfully!");
                    alert.show();



                }


            });

        });



    }



    @FXML
    private void listedemande() throws IOException {
        ServiceDemande so = new ServiceDemande();
        ArrayList arrayList = null;
        arrayList = (ArrayList) so.getAllDemande();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        id_liste.setItems(observableList);
        id_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        id_competences.setCellValueFactory(new PropertyValueFactory<>("competences"));
    }



    private void retour(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("com/fithnity/view/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void addCV(ActionEvent actionEvent) {
    }

    @FXML
    public void add_grise(ActionEvent actionEvent) {
    }

    @FXML
    public void add_lettre(ActionEvent actionEvent) {
    }

    @FXML
    public void ajouter_demande(ActionEvent actionEvent) {
    }


    public void reload() throws SQLException {
        ServiceDemande crud = new ServiceDemande();
        ObservableList<Demande> Demandes = FXCollections.observableArrayList(crud.getAllDemande());

        List<String> Demands = new ArrayList<>();


        ObservableList<String> observableList = FXCollections.observableArrayList(Demands);

        id_liste3.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

            }
        });
        id_liste3.setItems(observableList);
    }

    @FXML
    private void fx_afiche_demandePage(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("/com/fithnity/view/Table_view.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}






